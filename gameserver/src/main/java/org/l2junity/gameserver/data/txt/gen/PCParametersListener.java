// Generated from org\l2junity\gameserver\data\txt\gen\PCParameters.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PCParametersParser}.
 */
public interface PCParametersListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(PCParametersParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(PCParametersParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#base_physical_attack_table}.
	 * @param ctx the parse tree
	 */
	void enterBase_physical_attack_table(PCParametersParser.Base_physical_attack_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#base_physical_attack_table}.
	 * @param ctx the parse tree
	 */
	void exitBase_physical_attack_table(PCParametersParser.Base_physical_attack_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#base_physical_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_physical_attack(PCParametersParser.Base_physical_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#base_physical_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_physical_attack(PCParametersParser.Base_physical_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#pc_collision_box_table}.
	 * @param ctx the parse tree
	 */
	void enterPc_collision_box_table(PCParametersParser.Pc_collision_box_tableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#pc_collision_box_table}.
	 * @param ctx the parse tree
	 */
	void exitPc_collision_box_table(PCParametersParser.Pc_collision_box_tableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#collision_stat}.
	 * @param ctx the parse tree
	 */
	void enterCollision_stat(PCParametersParser.Collision_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#collision_stat}.
	 * @param ctx the parse tree
	 */
	void exitCollision_stat(PCParametersParser.Collision_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#pc_name}.
	 * @param ctx the parse tree
	 */
	void enterPc_name(PCParametersParser.Pc_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#pc_name}.
	 * @param ctx the parse tree
	 */
	void exitPc_name(PCParametersParser.Pc_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_object(PCParametersParser.Identifier_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_object(PCParametersParser.Identifier_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void enterBool_object(PCParametersParser.Bool_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void exitBool_object(PCParametersParser.Bool_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void enterByte_object(PCParametersParser.Byte_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void exitByte_object(PCParametersParser.Byte_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#int_object}.
	 * @param ctx the parse tree
	 */
	void enterInt_object(PCParametersParser.Int_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#int_object}.
	 * @param ctx the parse tree
	 */
	void exitInt_object(PCParametersParser.Int_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#long_object}.
	 * @param ctx the parse tree
	 */
	void enterLong_object(PCParametersParser.Long_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#long_object}.
	 * @param ctx the parse tree
	 */
	void exitLong_object(PCParametersParser.Long_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#double_object}.
	 * @param ctx the parse tree
	 */
	void enterDouble_object(PCParametersParser.Double_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#double_object}.
	 * @param ctx the parse tree
	 */
	void exitDouble_object(PCParametersParser.Double_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#string_object}.
	 * @param ctx the parse tree
	 */
	void enterString_object(PCParametersParser.String_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#string_object}.
	 * @param ctx the parse tree
	 */
	void exitString_object(PCParametersParser.String_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#name_object}.
	 * @param ctx the parse tree
	 */
	void enterName_object(PCParametersParser.Name_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#name_object}.
	 * @param ctx the parse tree
	 */
	void exitName_object(PCParametersParser.Name_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#category_object}.
	 * @param ctx the parse tree
	 */
	void enterCategory_object(PCParametersParser.Category_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#category_object}.
	 * @param ctx the parse tree
	 */
	void exitCategory_object(PCParametersParser.Category_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void enterVector3D_object(PCParametersParser.Vector3D_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void exitVector3D_object(PCParametersParser.Vector3D_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_list(PCParametersParser.Empty_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_list(PCParametersParser.Empty_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_list(PCParametersParser.Identifier_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_list(PCParametersParser.Identifier_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#int_list}.
	 * @param ctx the parse tree
	 */
	void enterInt_list(PCParametersParser.Int_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#int_list}.
	 * @param ctx the parse tree
	 */
	void exitInt_list(PCParametersParser.Int_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#double_list}.
	 * @param ctx the parse tree
	 */
	void enterDouble_list(PCParametersParser.Double_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#double_list}.
	 * @param ctx the parse tree
	 */
	void exitDouble_list(PCParametersParser.Double_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_attribute_attack(PCParametersParser.Base_attribute_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_attribute_attack(PCParametersParser.Base_attribute_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttack_attribute(PCParametersParser.Attack_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttack_attribute(PCParametersParser.Attack_attributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(PCParametersParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(PCParametersParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCParametersParser#category_list}.
	 * @param ctx the parse tree
	 */
	void enterCategory_list(PCParametersParser.Category_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCParametersParser#category_list}.
	 * @param ctx the parse tree
	 */
	void exitCategory_list(PCParametersParser.Category_listContext ctx);
}