// Generated from org\l2junity\gameserver\data\txt\gen\Lang.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_object(LangParser.Identifier_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_object(LangParser.Identifier_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void enterBool_object(LangParser.Bool_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void exitBool_object(LangParser.Bool_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void enterByte_object(LangParser.Byte_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void exitByte_object(LangParser.Byte_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#int_object}.
	 * @param ctx the parse tree
	 */
	void enterInt_object(LangParser.Int_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#int_object}.
	 * @param ctx the parse tree
	 */
	void exitInt_object(LangParser.Int_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#long_object}.
	 * @param ctx the parse tree
	 */
	void enterLong_object(LangParser.Long_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#long_object}.
	 * @param ctx the parse tree
	 */
	void exitLong_object(LangParser.Long_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#double_object}.
	 * @param ctx the parse tree
	 */
	void enterDouble_object(LangParser.Double_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#double_object}.
	 * @param ctx the parse tree
	 */
	void exitDouble_object(LangParser.Double_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#string_object}.
	 * @param ctx the parse tree
	 */
	void enterString_object(LangParser.String_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#string_object}.
	 * @param ctx the parse tree
	 */
	void exitString_object(LangParser.String_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#name_object}.
	 * @param ctx the parse tree
	 */
	void enterName_object(LangParser.Name_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#name_object}.
	 * @param ctx the parse tree
	 */
	void exitName_object(LangParser.Name_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#category_object}.
	 * @param ctx the parse tree
	 */
	void enterCategory_object(LangParser.Category_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#category_object}.
	 * @param ctx the parse tree
	 */
	void exitCategory_object(LangParser.Category_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void enterVector3D_object(LangParser.Vector3D_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void exitVector3D_object(LangParser.Vector3D_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_list(LangParser.Empty_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_list(LangParser.Empty_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_list(LangParser.Identifier_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_list(LangParser.Identifier_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#int_list}.
	 * @param ctx the parse tree
	 */
	void enterInt_list(LangParser.Int_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#int_list}.
	 * @param ctx the parse tree
	 */
	void exitInt_list(LangParser.Int_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#double_list}.
	 * @param ctx the parse tree
	 */
	void enterDouble_list(LangParser.Double_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#double_list}.
	 * @param ctx the parse tree
	 */
	void exitDouble_list(LangParser.Double_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_attribute_attack(LangParser.Base_attribute_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_attribute_attack(LangParser.Base_attribute_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttack_attribute(LangParser.Attack_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttack_attribute(LangParser.Attack_attributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(LangParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(LangParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#category_list}.
	 * @param ctx the parse tree
	 */
	void enterCategory_list(LangParser.Category_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#category_list}.
	 * @param ctx the parse tree
	 */
	void exitCategory_list(LangParser.Category_listContext ctx);
}