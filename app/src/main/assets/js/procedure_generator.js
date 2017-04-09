Blockly.JavaScript['procedures_nreturn'] = function(block) {
  var text_fname = block.getFieldValue('FNAME');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = 'function '+text_fname+'(){\n'+statements_name+'}\n';
  return code;
};

Blockly.JavaScript['procedures_return'] = function(block) {
  var text_fname = block.getFieldValue('FNAME');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  var value_result = Blockly.JavaScript.valueToCode(block, 'result', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = 'function '+text_fname+'(){\n'+statements_name+'return '+value_result+';\n}\n';
  return code;
};

Blockly.JavaScript['procedures_ifreturn'] = function(block) {
  var value_condition = Blockly.JavaScript.valueToCode(block, 'condition', Blockly.JavaScript.ORDER_ATOMIC);
  var value_result = Blockly.JavaScript.valueToCode(block, 'result', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = 'if('+value_condition+'){\n'+'return '+value_result+';\n}\n';
  return code;
};