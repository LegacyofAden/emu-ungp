grammar DecoDatas;

import Lang;

file : deco_data+;

deco_data : 'deco_begin' id name type level depth_ func cost 'deco_end';

id : 'id' '=' int_object;
name : 'name' '=' name_object;
type : 'type' '=' int_object; // TODO: To DecoType
level : 'level' '=' int_object;
depth_ : 'depth' '=' int_object;
func : 'func' '=' string_object; // TODO: To func_object
cost : 'cost' '=' string_object; // TODO: Parse over ':'