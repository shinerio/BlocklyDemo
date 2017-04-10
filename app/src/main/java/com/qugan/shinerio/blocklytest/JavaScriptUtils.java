package com.qugan.shinerio.blocklytest;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * Created by jstxzhangrui on 2017/4/9.
 */
public class JavaScriptUtils {
    private static  String JAVA_CALL_JS_FUNCTION = "function defaultFunction(){ return 'no code execute,copyright by shinerio'; }";

    public static void setJavaScript(String script){
        if(script!=null&&!"".equals(script))
        JAVA_CALL_JS_FUNCTION=script;
    }

    public static String runScript(android.content.Context context){
            String result = "";
            String functionName = getFunctionName();
            result = runScript(context,JAVA_CALL_JS_FUNCTION,functionName,new String[] {});
        return result;
    }

    public static String runScript(android.content.Context context,String js, String functionName, Object[] functionParams) {
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        try {
            Scriptable scope = rhino.initStandardObjects();

            ScriptableObject.putProperty(scope, "javaContext", Context.javaToJS(context, scope));
            ScriptableObject.putProperty(scope, "javaLoader", Context.javaToJS(context.getClass().getClassLoader(), scope));

            rhino.evaluateString(scope, js, "activity", 1, null);

            Function function = (Function) scope.get(functionName, scope);
            Object result = function.call(rhino, scope, scope, functionParams);
            if (result instanceof String) {
                return (String) result;
            } else if (result instanceof NativeJavaObject) {
                return (String) ((NativeJavaObject) result).getDefaultValue(String.class);
            } else if (result instanceof NativeObject) {
                return (String) ((NativeObject) result).getDefaultValue(String.class);
            }
            return result.toString();//(String) function.call(rhino, scope, scope, functionParams);
        } finally {
            Context.exit();
        }
    }

    private static String getFunctionName(){
        return JAVA_CALL_JS_FUNCTION.substring(JAVA_CALL_JS_FUNCTION.indexOf("function")+8,JAVA_CALL_JS_FUNCTION.indexOf("(")).trim();
    }
}
