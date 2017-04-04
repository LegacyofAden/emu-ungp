grammar DecoDatas;

import Lang;

@header {
import org.l2junity.gameserver.data.txt.model.decodata.DecoCostData;
import org.l2junity.gameserver.data.txt.model.decodata.DecoFunctionType;
}

file : decoData+;

decoData : 'deco_begin' id name type level depth_ funcValue cost 'deco_end';

id returns[int value] :
	'id'	'='	io=int_object {$ctx.value = $io.value;};
name returns[String value] :
 	'name'	'='	no=name_object {$ctx.value = $no.value;};
type returns[DecoFunctionType value] :
 	'type'	'='	dt=deco_type {$ctx.value = $dt.value;};
level returns[int value] :
	'level'	'='	io=int_object {$ctx.value = $io.value;};
depth_ returns[int value] :
	'depth'	'='	io=int_object {$ctx.value = $io.value;};
funcValue returns[double value] :
	'func'	'='	(NONE | (HP_REGEN | MP_REGEN | XP_RESTORE) '(' d=double_object {$ctx.value=$d.value;} ')') ;
cost returns[DecoCostData value] :
	'cost'	'='	co=cost_object {$ctx.value = $co.value;};

deco_type
    returns[DecoFunctionType value] :
    '0' {$ctx.value = DecoFunctionType.NONE;}
    | '1' {$ctx.value = DecoFunctionType.HP_REGEN;}
    | '2' {$ctx.value = DecoFunctionType.MP_REGEN;}
    | '3' {$ctx.value = DecoFunctionType.CP_REGEN;}
    | '4' {$ctx.value = DecoFunctionType.XP_RESTORE;}
    | '5' {$ctx.value = DecoFunctionType.TELEPORT;}
    | '6' {$ctx.value = DecoFunctionType.BROADCAST;}
    | '7' {$ctx.value = DecoFunctionType.CURTAIN;}
    | '8' {$ctx.value = DecoFunctionType.HANGING;}
    | '9' {$ctx.value = DecoFunctionType.OUTERPLATFORM;}
    | '10' {$ctx.value = DecoFunctionType.PLATFORM;}
    | '11' {$ctx.value = DecoFunctionType.ITEM_CREATE;}
    | '12' {$ctx.value = DecoFunctionType.BUFF;};

cost_object
    returns[DecoCostData value]
    @after{$ctx.value = new DecoCostData($days.value, $adena.value);}
    : '{' days=int_object ':' adena=int_object '}';

NONE : 'none';
HP_REGEN : 'hp_regen';
MP_REGEN : 'mp_regen';
CP_REGEN : 'cp_regen';
XP_RESTORE : 'exp_restore';
TELEPORT : 'teleport';
BROADCAST : 'broadcast';
CURTAIN : 'curtain';
HANGING : 'hanging';
OUTERPLATFORM : 'outerplatform';
PLATFORM : 'platform';
ITEM_CREATE : 'item_create';
BUFF : 'buff';