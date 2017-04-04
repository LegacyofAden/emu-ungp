// Generated from org\l2junity\gameserver\data\txt\gen\UserBasicActions.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.constants.ActionHandlerType;
import org.l2junity.gameserver.data.txt.model.action.ActionTemplate;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link UserBasicActionsParser}.
 */
public interface UserBasicActionsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(UserBasicActionsParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(UserBasicActionsParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(UserBasicActionsParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(UserBasicActionsParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(UserBasicActionsParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(UserBasicActionsParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#handler}.
	 * @param ctx the parse tree
	 */
	void enterHandler(UserBasicActionsParser.HandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#handler}.
	 * @param ctx the parse tree
	 */
	void exitHandler(UserBasicActionsParser.HandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(UserBasicActionsParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(UserBasicActionsParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#handler_type}.
	 * @param ctx the parse tree
	 */
	void enterHandler_type(UserBasicActionsParser.Handler_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#handler_type}.
	 * @param ctx the parse tree
	 */
	void exitHandler_type(UserBasicActionsParser.Handler_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_object(UserBasicActionsParser.Identifier_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#identifier_object}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_object(UserBasicActionsParser.Identifier_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void enterBool_object(UserBasicActionsParser.Bool_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#bool_object}.
	 * @param ctx the parse tree
	 */
	void exitBool_object(UserBasicActionsParser.Bool_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void enterByte_object(UserBasicActionsParser.Byte_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#byte_object}.
	 * @param ctx the parse tree
	 */
	void exitByte_object(UserBasicActionsParser.Byte_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#int_object}.
	 * @param ctx the parse tree
	 */
	void enterInt_object(UserBasicActionsParser.Int_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#int_object}.
	 * @param ctx the parse tree
	 */
	void exitInt_object(UserBasicActionsParser.Int_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#long_object}.
	 * @param ctx the parse tree
	 */
	void enterLong_object(UserBasicActionsParser.Long_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#long_object}.
	 * @param ctx the parse tree
	 */
	void exitLong_object(UserBasicActionsParser.Long_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#double_object}.
	 * @param ctx the parse tree
	 */
	void enterDouble_object(UserBasicActionsParser.Double_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#double_object}.
	 * @param ctx the parse tree
	 */
	void exitDouble_object(UserBasicActionsParser.Double_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#string_object}.
	 * @param ctx the parse tree
	 */
	void enterString_object(UserBasicActionsParser.String_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#string_object}.
	 * @param ctx the parse tree
	 */
	void exitString_object(UserBasicActionsParser.String_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#name_object}.
	 * @param ctx the parse tree
	 */
	void enterName_object(UserBasicActionsParser.Name_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#name_object}.
	 * @param ctx the parse tree
	 */
	void exitName_object(UserBasicActionsParser.Name_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#category_object}.
	 * @param ctx the parse tree
	 */
	void enterCategory_object(UserBasicActionsParser.Category_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#category_object}.
	 * @param ctx the parse tree
	 */
	void exitCategory_object(UserBasicActionsParser.Category_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void enterVector3D_object(UserBasicActionsParser.Vector3D_objectContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#vector3D_object}.
	 * @param ctx the parse tree
	 */
	void exitVector3D_object(UserBasicActionsParser.Vector3D_objectContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_list(UserBasicActionsParser.Empty_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#empty_list}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_list(UserBasicActionsParser.Empty_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_list(UserBasicActionsParser.Identifier_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#identifier_list}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_list(UserBasicActionsParser.Identifier_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#int_list}.
	 * @param ctx the parse tree
	 */
	void enterInt_list(UserBasicActionsParser.Int_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#int_list}.
	 * @param ctx the parse tree
	 */
	void exitInt_list(UserBasicActionsParser.Int_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#double_list}.
	 * @param ctx the parse tree
	 */
	void enterDouble_list(UserBasicActionsParser.Double_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#double_list}.
	 * @param ctx the parse tree
	 */
	void exitDouble_list(UserBasicActionsParser.Double_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void enterBase_attribute_attack(UserBasicActionsParser.Base_attribute_attackContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#base_attribute_attack}.
	 * @param ctx the parse tree
	 */
	void exitBase_attribute_attack(UserBasicActionsParser.Base_attribute_attackContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttack_attribute(UserBasicActionsParser.Attack_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#attack_attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttack_attribute(UserBasicActionsParser.Attack_attributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(UserBasicActionsParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(UserBasicActionsParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link UserBasicActionsParser#category_list}.
	 * @param ctx the parse tree
	 */
	void enterCategory_list(UserBasicActionsParser.Category_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UserBasicActionsParser#category_list}.
	 * @param ctx the parse tree
	 */
	void exitCategory_list(UserBasicActionsParser.Category_listContext ctx);
}