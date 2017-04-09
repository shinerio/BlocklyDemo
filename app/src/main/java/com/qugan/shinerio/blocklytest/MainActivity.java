package com.qugan.shinerio.blocklytest;

import android.support.annotation.NonNull;

import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.codegen.CodeGenerationRequest;
import com.google.blockly.android.codegen.LoggingCodeGeneratorCallback;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AbstractBlocklyActivity {

    private static final String TAG = "MainActivity";
    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
       return "toolbox.xml";
    }

    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        return Arrays.asList(
                "default/list_blocks.json",
                "default/logic_blocks.json",
                "default/loop_blocks.json",
                "default/math_blocks.json",
                "default/test_blocks.json",
                "default/text_blocks.json",
                "default/variable_blocks.json",
                "default/colour_blocks.json",
                "json/procedure_blocks.json"
        );
    }
    private static final List<String> JAVASCRIPT_GENERATORS = Arrays.asList(
            "js/procedure_generator.js"
    );
    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
            return JAVASCRIPT_GENERATORS;
    }
//    CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
//         new   CodeGenerationRequest.CodeGeneratorCallback() {
//        @Override
//        public void onFinishCodeGeneration(String s){
//            String result = runScript(JAVA_CALL_JS_FUNCTION, "Test", new String[] {});
//            Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
//        }
//    };
CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback = new LoggingCodeGeneratorCallback(this,"MainActivity");
    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        return mCodeGeneratorCallback;
    }

    @Override
    protected void onInitBlankWorkspace() {
        // Initialize available variable names.
        getController().addVariable("item");
    }
}
