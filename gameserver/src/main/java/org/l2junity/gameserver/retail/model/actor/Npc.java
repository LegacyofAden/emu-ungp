package org.l2junity.gameserver.retail.model.actor;

import org.apache.commons.lang3.StringUtils;
import org.l2junity.gameserver.network.packets.s2c.StaticObject;
import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.ai.NpcEventHandler;
import org.l2junity.gameserver.retail.model.*;
import org.l2junity.gameserver.retail.model.formation.Clan;
import org.l2junity.gameserver.retail.model.formation.Party;
import org.l2junity.gameserver.retail.model.item.BuySellItemInfo;
import org.l2junity.gameserver.retail.model.item.ItemData;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class Npc {
    @AiEventId(1104)
    public int p_state;

    @AiEventId(1112)
    public int i_quest0;

    @AiEventId(1120)
    public int i_quest1;

    @AiEventId(1128)
    public int i_quest2;

    @AiEventId(1136)
    public int i_quest3;

    @AiEventId(1144)
    public int i_quest4;

    @AiEventId(1152)
    public int i_quest5;

    @AiEventId(1160)
    public int i_quest6;

    @AiEventId(1168)
    public int i_quest7;

    @AiEventId(1176)
    public int i_quest8;

    @AiEventId(1184)
    public int i_quest9;

    @AiEventId(1192)
    public int i_ai0;

    @AiEventId(1200)
    public int i_ai1;

    @AiEventId(1208)
    public int i_ai2;

    @AiEventId(1216)
    public int i_ai3;

    @AiEventId(1224)
    public int i_ai4;

    @AiEventId(1232)
    public int i_ai5;

    @AiEventId(1240)
    public int i_ai6;

    @AiEventId(1248)
    public int i_ai7;

    @AiEventId(1256)
    public int i_ai8;

    @AiEventId(1264)
    public int i_ai9;

    @AiEventId(1272)
    public int attack_tick;

    @AiEventId(1280)
    public Creature c_quest0;

    @AiEventId(1288)
    public Creature c_quest1;

    @AiEventId(1296)
    public Creature c_quest2;

    @AiEventId(1304)
    public Creature c_quest3;

    @AiEventId(1312)
    public Creature c_quest4;

    @AiEventId(1320)
    public Creature c_ai0;

    @AiEventId(1328)
    public Creature c_ai1;

    @AiEventId(1336)
    public Creature c_ai2;

    @AiEventId(1344)
    public Creature c_ai3;

    @AiEventId(1352)
    public Creature c_ai4;

    @AiEventId(1528)
    public IntList int_list;

    @AiEventId(1536)
    public int m_WorldTrapState;

    @AiEventId(1544)
    public Creature sm;

    @AiEventId(1552)
    public Creature master;

    @AiEventId(1560)
    public Creature boss;

    @AiEventId(1568)
    public Creature top_desire_target;

    @AiEventId(1588)
    public int start_x;

    @AiEventId(1592)
    public int start_y;

    @AiEventId(1596)
    public int start_z;

    @AiEventId(1684)
    public int sit_on_stop;

    @AiEventId(1424)
    public AtomicValue av_quest0;

    @AiEventId(1432)
    public AtomicValue av_quest1;

    @AiEventId(1440)
    public AtomicValue av_ai0;

    private NpcEventHandler eventHandler;

    public NpcEventHandler getEventHandler() {
        /*if (eventHandler == null) {
            eventHandler = new NpcEventHandler(this);
        }
        return eventHandler;*/
        return null;
    }

    @AiEventId(235077737)
    public int PointDistFromMe(int x, int y, int z) {
        return 0;
    }

    @AiEventId(235012179)
    public void ShowPage(Creature talker, String page) {
    }

    @AiEventId(234946562)
    public int IsNullParty(Party party) {
        return party == null ? 0 : 1;
    }

    @AiEventId(234946604)
    public int GetDirectionToTarget(Creature creature) {
        return 0;
    }

    @AiEventId(235012711)
    public void Whisper(Creature creature, String msg) {
    }

    @AiEventId(235340392)
    public void WhisperFStr(Creature creature, int fstrId, String param0, String param1, String param2, String param3, String param4) {
    }

    @AiEventId(234946818)
    public int Skill_GetAbnormalType(int skillId) {
        return 0;
    }

    @AiEventId(235012644)
    public void Dispel(Creature creature, int abnormal_type) {
    }

    @AiEventId(235012146)
    public void AddTimerEx(int timer_id, int delay) {
    }

    @AiEventId(235602190)
    public int CreateOnePrivateEx(int npc_id, String ai_type, int i1, int i2, int x, int y, int z, int heading, int i7,
                                  int i8, int i9) {
        return 0;
    }

    @AiEventId(235143194)
    public void AddEffectActionDesire(Creature creature, int action_id, int i1, double point) {
    }

    @AiEventId(234881229)
    public int Castle_GetPledgeId() {
        return 0;
    }

    @AiEventId(235012251)
    public void FHTML_SetFileName(FHTML fhtml, String filename) {
    }

    @AiEventId(235077788)
    public void FHTML_SetInt(FHTML fhtml, String find, int replacement) {
    }

    @AiEventId(234881197)
    public String Castle_GetPledgeName() {
        return "";
    }

    @AiEventId(234881198)
    public String Castle_GetOwnerName() {
        return "";
    }

    @AiEventId(234946755)
    public int Castle_GetPledgeReserveState(Creature talker) {
        return 0;
    }

    @AiEventId(234881201)
    public int Residence_GetTaxRateCurrent() {
        return 0;
    }

    @AiEventId(234946743)
    public int Residence_GetCastleTypeByID(int id) {
        return 0;
    }

    @AiEventId(234946742)
    public void Residence_ResetCastle(Creature creature) {
    }

    @AiEventId(235012256)
    public void ShowFHTML(Creature creature, FHTML fhtml) {
    }

    @AiEventId(235012198)
    public void ShowMultisell(int multisell_id, Creature talker) {
    }

    @AiEventId(234947066)
    public void ShowVariationMakeWindow(Creature talker) {
    }

    @AiEventId(234947067)
    public void ShowVariationCancelWindow(Creature talker) {
    }

    @AiEventId(235078155)
    public void InstantZone_Enter(Creature talker, int zone_type, int enter_type) {
    }

    @AiEventId(235143692)
    public void InstantZone_Leave(Creature talker, int unk0, int unk1, int unk2) {
    }

    @AiEventId(235143698)
    public void InstantZone_Leave_Ex(Creature talker, int unk0, int unk1, int unk2) {
    }

    @AiEventId(234947085)
    public void InstantZone_Finish(int delay) {
    }

    @AiEventId(235012224)
    public Creature GetMemberOfParty(Party party, int index) {
        return null;
    }

    @AiEventId(235012343)
    public int IsInCategory(int category_id, int cat) {
        return 0;
    }

    @AiEventId(235012218)
    public int GetOneTimeQuestFlag(Creature creature, int quest) {
        return 0;
    }

    @AiEventId(234946695)
    public Clan GetPledge(Creature creature) {
        return null;
    }

    @AiEventId(234946560)
    public int IsNullCreature(Creature creature) {
        return creature == null ? 1 : 0;
    }

    @AiEventId(234946732)
    public int IsMyLord(Creature creature) {
        return 0;
    }

    @AiEventId(234881220)
    public int Castle_IsUnderSiege() {
        return 0;
    }

    @AiEventId(234946764)
    public int Castle_IsUnderSiege2(int unk) {
        return 0;
    }

    @AiEventId(234881226)
    public int Castle_GetRawSiegeTime() {
        return 0;
    }

    @AiEventId(234881227)
    public int Castle_GetRawSystemTime() {
        return 0;
    }

    @AiEventId(234946750)
    public void Castle_SetCastleType(int type) {
    }

    @AiEventId(234946687)
    public Creature GetLeaderOfParty(Party party) {
        return null;
    }

    @AiEventId(235077782)
    public void DeleteItem1(Creature talker, int itemId, long count) {
    }

    @AiEventId(235077778)
    public void GiveItem1(Creature talker, int itemId, int count) {
    }

    @AiEventId(235077779)
    public void GiveQuestItem(Creature talker, int itemId, int count) {
    }

    @AiEventId(235077751)
    public void SetFlagJournal(Creature talker, int quest, int state) {
    }

    @AiEventId(235012523)
    public void ShowQuestMark(Creature talker, int quest) {
    }

    @AiEventId(235012372)
    public void SoundEffect(Creature talker, String sound) {
    }

    @AiEventId(234946955)
    public int GetSSQSealOwner(int seal) {
        return 0;
    }

    @AiEventId(234881417)
    public int GetSSQStatus() {
        return 0;
    }

    @AiEventId(235012409)
    public void RideWyvern(Creature talker, int wyvern) {
    }

    @AiEventId(235012568)
    public void CastBuffForQuestReward(Creature talker, int skill_id) {
    }

    @AiEventId(234946671)
    public void OpenHennaItemListForEquip(Creature talker) {
    }

    @AiEventId(234946672)
    public void OpenHennaListForUnquip(Creature talker) {
    }

    @AiEventId(235077639)
    public void AddAttackDesire(Creature talker, int desire_id, double point) {
    }

    @AiEventId(235143193)
    public void AddMoveToDesire(int x, int y, int z, double point) {
    }

    @AiEventId(235012102)
    public void AddDoNothingDesire(int unk, double point) {
    }

    @AiEventId(235012100)
    public void AddMoveAroundDesire(int unk, double point) {
    }

    @AiEventId(235208725)
    public void AddUseSkillDesire(Creature talker, int skill_id, int unk1, int unk2, double point) {
    }

    @AiEventId(234946843)
    public int InMyTerritory(Creature creature) {
        return 0;
    }

    @AiEventId(235143263)
    public void InstantTeleport(Creature creature, int x, int y, int z) {
    }

    @AiEventId(234881122)
    public void InstantRandomTeleportInMyTerritory() {
    }

    @AiEventId(235143188)
    public void AddMoveToWayPointDesire(WayPointsType wayPointsType, WayPointDelaysType wayPointDelaysType, int index,
                                        double point) {
    }

    @AiEventId(235012205)
    public int GetWayPointDelay(WayPointDelaysType wayPointDelays, int way_point_index) {
        return 0;
    }

    @AiEventId(234947060)
    public int GetPledgeMemberCount(Creature talker) {
        return 0;
    }

    @AiEventId(234947171)
    public int IsLordOfCastle(Creature talker) {
        return 0;
    }

    @AiEventId(235077906)
    public void IncrementParam(Creature talker, int param, int value) {
    }

    @AiEventId(235012391)
    public void PledgeLevelUp(Creature talker, int level) {
    }

    @AiEventId(234946816)
    public int Skill_GetConsumeMP(int skill_id) {
        return 0;
    }

    @AiEventId(234946815)
    public int Skill_GetConsumeHP(int skill_id) {
        return 0;
    }

    @AiEventId(234946820)
    public int Skill_InReuseDelay(int skill_id) {
        return 0;
    }

    @AiEventId(234947053)
    public int OwnPledgeNameValue(Creature talker) {
        return 0;
    }

    @AiEventId(235012588)
    public void UpdatePledgeNameValue(Creature talker, int value) {
    }

    @AiEventId(234947140)
    public int IsDominionOfLord(int dominion_id) {
        return 0;
    }

    @AiEventId(234947057)
    public int HasAcademy(Creature talker) {
        return 0;
    }

    @AiEventId(235012222)
    public int HavePledgePower(Creature talker, int unk) {
        return 0;
    }

    @AiEventId(235012590)
    public void CreateAcademy(Creature talker, String name) {
    }

    @AiEventId(235012592)
    public int HasSubPledge(Creature talker, int unk) {
        return 0;
    }

    @AiEventId(235143663)
    public void CreateSubPledge(Creature talker, int unk1, int unk2, String name) {
    }

    @AiEventId(235143414)
    public void ShowGrowEtcSkillMessage(Creature talker, int skill_name_id, int unk1, String unk2) {
    }

    @AiEventId(235077877)
    public void ShowEtcSkillList(Creature talker, int unk1, String unk2) {
    }

    @AiEventId(234947091)
    public void ShowChangePledgeNameUI(Creature talker) {
    }

    @AiEventId(234946563)
    public int IsNullString(String str) {
        return StringUtils.isEmpty(str) ? 0 : 1;
    }

    @AiEventId(234946723)
    public int GetPledgeSkillLevel(Creature talker) {
        return 0;
    }

    @AiEventId(235012181)
    public void ShowSystemMessage(Creature talker, int msg_id) {
    }

    @AiEventId(235077755)
    public void SetOneTimeQuestFlag(Creature talker, int quest_num, int flag) {
    }

    @AiEventId(235012220)
    public int GetInventoryInfo(Creature talker, int param) {
        return 0;
    }

    @AiEventId(235012344)
    public void ClassChange(Creature talker, int class_lv) {
    }

    @AiEventId(235077881)
    public void ClassChangeEx(Creature talker, int class_id, int level) {
    }

    @AiEventId(234946890)
    public int GetMainClassLevel(Creature talker) {
        return 0;
    }

    @AiEventId(234946891)
    public int GetDualClassID(Creature talker) {
        return 0;
    }

    @AiEventId(234947047)
    public int IsAcademyMember(Creature talker) {
        return 0;
    }

    @AiEventId(235012753)
    public Creature GetSummonByIndex(Creature talker, int index) {
        return null;
    }

    @AiEventId(235143177)
    public void AddAllSummonAttackDesire(Creature talker, int unk0, int unk1, double damage) {
    }

    @AiEventId(235077954)
    public void GetSubJobList(Creature talker, int unk1, int state) {
    }

    @AiEventId(234946887)
    public int GetSubJobCount(Creature talker) {
        return 0;
    }

    @AiEventId(235012420)
    public void ChangeSubJob(Creature talker, int subjob) {
    }

    @AiEventId(235012419)
    public void CreateSubJob(Creature talker, int subjob) {
    }

    @AiEventId(235143493)
    public void RenewSubJob(Creature talker, int unk, int subjob, int unk0) {
    }

    @AiEventId(235012336)
    public void ShowSkillList(Creature talker, String str) {
    }

    @AiEventId(235077874)
    public void ShowGrowSkillMessage(Creature talker, int skill_name_id, String unk) {
    }

    @AiEventId(235012337)
    public void ShowEnchantSkillList(Creature talker, int action_id) {
    }

    @AiEventId(234946629)
    public void Say(String str) {
    }

    @AiEventId(234946636)
    public void DebugSay(String str) {
    }

    @AiEventId(235274310)
    public void SayFStr(int unk0, String unk1, String unk2, String unk3, String unk4, String unk5) {
    }

    @AiEventId(235078151)
    public void ShowEnchantSkillListDrawer(Creature talker, int skill_name_id, int action_id) {
    }

    @AiEventId(235077876)
    public void ShowEnchantSkillMessage(Creature talker, int skill_name_id, int action_id) {
    }

    @AiEventId(235012646)
    public void DeleteAcquireSkills(Creature talker, int unk) {
    }

    @AiEventId(235143680)
    public void AddLogByNpc(int unk, Creature talker, int unk1, int unk2) {
    }

    @AiEventId(234947093)
    public int GetPledgeCastleSiegeDefenceCount(Creature talker) {
        return 0;
    }

    @AiEventId(235209238)
    public void GiveItemByCastleSiegeDefence(Creature talker, int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(235143269)
    public void ShowBuySell(Creature talker, BuySellItemInfo[] Buylist, BuySellItemInfo[] SellList, int unk) {
    }

    @AiEventId(235274340)
    public void SellPreview(Creature talker, BuySellItemInfo[] SellList, String shopName, String html, String unk1,
                            String unk2) {
    }

    @AiEventId(235012249)
    public ItemData GetItemData(Creature talker, int item_id) {
        return null;
    }

    @AiEventId(235143477)
    public void CreatePet(Creature talker, int item_id, int unk1, int unk2) {
    }

    @AiEventId(235339863)
    public void ShowSystemMessageFStr(Creature target, int fStringId, String fString1, String fString2,
                                      String fString3, String fString4, String fString5) {
    }

    @AiEventId(234946664)
    public int DistFromMe(Creature creature) {
        return 0;
    }

    @AiEventId(235012408)
    public void EvolvePetWithSameExp(Creature creature, int unk1) {
    }

    @AiEventId(235274550)
    public void EvolvePet(Creature talker, int item_dbid, int class_id_baby_pet, int item_grown_pet,
                          int class_id_grown_pet,
                          int item_pet_level) {
    }

    @AiEventId(234946753)
    public void Castle_GateOpenClose(int value) {
    }

    @AiEventId(234881304)
    public void Despawn() {
    }

    @AiEventId(234947114)
    public int GetPVPPoint(Creature talker) {
        return 0;
    }

    @AiEventId(235012648)
    public void UpdatePVPPoint(Creature talker, int diff) {
    }

    @AiEventId(234947014)
    public void SetNobless(Creature talker) {
    }

    @AiEventId(234946985)
    public int IsNewbie(Creature talker) {
        return 0;
    }

    @AiEventId(234881507)
    public int GetFishingEventRewardRemainTime() {
        return 0;
    }

    @AiEventId(234947046)
    public void GiveFishingEventPrize(Creature talker) {
    }

    @AiEventId(234947045)
    public void ShowHtmlFishingEventRanking(Creature talker) {
    }

    @AiEventId(234947044)
    public int GetFishingEventRanking(Creature talker) {
        return 0;
    }

    @AiEventId(235012480)
    public void ShowSellSeedList(Creature talker, int manor_id) {
    }

    @AiEventId(235012481)
    public void ShowProcureCropList(Creature talker, int manor_id) {
    }

    @AiEventId(235012462)
    public int GetCurrentSeedSellCountSet(int state, int index) {
        return 0;
    }

    @AiEventId(235012464)
    public int GetCurrentSeedPrice(int state, int index) {
        return 0;
    }

    @AiEventId(235012463)
    public int GetCurrentSeedRemainCount(int state, int index) {
        return 0;
    }

    @AiEventId(235012473)
    public int GetRemainProcureCropCount(int manor_id, int index) {
        return 0;
    }

    @AiEventId(235012471)
    public int GetProcurementCount(int manor_id, int index) {
        return 0;
    }

    @AiEventId(235012470)
    public int GetProcurementRate(int manor_id, int index) {
        return 0;
    }

    @AiEventId(235012472)
    public int GetProcurementType(int manor_id, int index) {
        return 0;
    }

    @AiEventId(235012475)
    public int GetNextProcurementCount(int state, int index) {
        return 0;
    }

    @AiEventId(235012474)
    public int GetNextProcurementRate(int state, int index) {
        return 0;
    }

    @AiEventId(235012476)
    public int GetNextProcurementType(int state, int index) {
        return 0;
    }

    @AiEventId(234881277)
    public int IsManorSettingTime() {
        return 0;
    }

    @AiEventId(235012465)
    public int GetNextSeedSellCountSet(int state, int index) {
        return 0;
    }

    @AiEventId(235012466)
    public int GetNextSeedPrice(int state, int index) {
        return 0;
    }

    @AiEventId(235078018)
    public void ShowSeedInfo(Creature talker, int id, int time) {
    }

    @AiEventId(235078019)
    public void ShowCropInfo(Creature talker, int id, int time) {
    }

    @AiEventId(234946948)
    public void ShowManorDefaultInfo(Creature talker) {
    }

    @AiEventId(235012487)
    public void ShowProcureCropDetail(Creature talker, int state) {
    }

    @AiEventId(235012647)
    public void RegisterTeleporterType(int type, int unk) {
    }

    @AiEventId(234881495)
    public int GetPCCafePointEventState() {
        return 0;
    }

    @AiEventId(234947028)
    public int GetPCCafePoint(Creature talker) {
        return 0;
    }

    @AiEventId(234881526)
    public int GetPCCafeCouponEventState() {
        return 0;
    }

    @AiEventId(234947061)
    public int IsPCCafeUser(Creature talker) {
        return 0;
    }

    @AiEventId(234946986)
    public void ShowQuestInfoList(Creature talker) {
    }

    @AiEventId(235012188)
    public void ShowTelPosListPage(Creature talker, TelPosInfo[] telPosInfos) {
    }

    @AiEventId(235012565)
    public int UpdatePCCafePoint(Creature talker, int diff) {
        return 0;
    }

    @AiEventId(235209174)
    public void GiveItemByPCCafePoint(Creature talker, int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(235012635)
    public void UpdatePCCafePoint2(Creature talker, int count) {
    }

    @AiEventId(234947069)
    public int IsInCombatMode(Creature creature) {
        return 0;
    }

    @AiEventId(235143482)
    public void RideForEvent(Creature talker, int pet_id, int unk1, int unk2) {
    }

    @AiEventId(235733085)
    public void TeleportFStr(Creature talker, TelPosInfo[] posInfos, String shopName, String shoparg0, String shoparg1, String shoparg2, int itemId, int stringId,
                             String str1, String str2, String str3, String str4, String unk) {
    }

    @AiEventId(234881448)
    public int CanLotto() {
        return 0;
    }

    @AiEventId(235143284)
    public void SetMemoStateEx(Creature talker, int quest_id, int state, int unk) {
    }

    @AiEventId(234947042)
    public void CheckCursedUser(Creature talker) {
    }

    @AiEventId(234881485)
    public NpcMaker GetMyMaker() {
        return null;
    }

    @AiEventId(234881069)
    public int GetMyDirection() {
        return 0;
    }

    @AiEventId(234946754)
    public int Castle_GetPledgeState(Creature talker) {
        return 0;
    }

    @AiEventId(234881230)
    public int Castle_GetLifeControlLevel() {
        return 0;
    }

    @AiEventId(235143453)
    public void InstantTeleportInMyTerritory(int x, int y, int z, int unk) {
    }

    @AiEventId(234946638)
    public void Shout(String str) {
    }

    @AiEventId(235274320)
    public void ShoutFStr(int shoutMsg1, String str0, String str1, String str2, String str3, String str4) {
    }

    @AiEventId(235339857)
    public void ShoutFStrEx(int shoutMsg1, String str0, String str1, String str2, String str3, String str4, int unk) {
    }

    @AiEventId(235601992)
    public void ShoutFStrExtension(int fstringId, String unk0, String str0, String str1, String str2, String str3, int unk1, int unk2, int unk3, int unk4, int unk5) {
    }

    @AiEventId(235078198)
    public void SetNRMemoState(Creature talker, int mission, int value) {
    }

    @AiEventId(234881200)
    public int Residence_GetTaxRate() {
        return 0;
    }

    @AiEventId(234881204)
    public int Residence_GetCastleType() {
        return 0;
    }

    @AiEventId(234881274)
    public int Residence_GetTaxIncome() {
        return 0;
    }

    @AiEventId(234881275)
    public int Residence_GetTaxIncomeReserved() {
        return 0;
    }

    @AiEventId(234881276)
    public int Manor_GetSeedIncome() {
        return 0;
    }

    @AiEventId(234946895)
    public void OpenSiegeInfo(Creature talker) {
    }

    @AiEventId(234946965)
    public int GetTicketBuyCount(Creature talker) {
        return 0;
    }

    @AiEventId(235012674)
    public void RegisterDominion(int dominion_id, Creature talker) {
    }

    @AiEventId(234946774)
    public int Agit_GetDecoLevel(int param) {
        return 0;
    }

    @AiEventId(235012315)
    public int Agit_GetDecoFee(int param, int deco_level) {
        return 0;
    }

    @AiEventId(235012314)
    public int Agit_GetDecoDay(int param, int deco_level) {
        return 0;
    }

    @AiEventId(234946775)
    public int Agit_GetDecoExpire(int param) {
        return 0;
    }

    @AiEventId(234946767)
    public void Castle_GetRelatedFortressList(Creature talker) {
    }

    @AiEventId(234946739)
    public void Residence_SetTaxRate(int value) {
    }

    @AiEventId(235077678)
    public void SetCookie(Creature talker, String param, int value) {
    }

    @AiEventId(235012143)
    public int GetCookie(Creature talker, String cookie) {
        return 0;
    }

    @AiEventId(235012433)
    public void GetDoorHpLevel(Creature talker, String door_name) {
    }

    @AiEventId(235012435)
    public void GetControlTowerLevel(Creature talker, String unk) {
    }

    @AiEventId(234881223)
    public void Castle_BanishOthers() {
    }

    @AiEventId(235012284)
    public void Residence_VaultTakeOutMoney(Creature talker, int reply) {
    }

    @AiEventId(235012285)
    public void Residence_VaultSaveMoney(Creature talker, int reply) {
    }

    @AiEventId(235012461)
    public int GetMaxSellableCount(int unk1, int unk2) {
        return 0;
    }

    @AiEventId(235012469)
    public int GetSeedDefaultPrice(int unk1, int unk2) {
        return 0;
    }

    @AiEventId(235012479)
    public int GetCropClassidByOrderNum(int unk, int reply) {
        return 0;
    }

    @AiEventId(235012502)
    public void SetTicketBuyCount(Creature talker, int unk) {
    }

    @AiEventId(235012309)
    public void Agit_ResetDeco(Creature talker, int i0) {
    }

    @AiEventId(235077844)
    public void Agit_SetDeco(Creature talker, int unk1, int unk2) {
    }

    @AiEventId(235012569)
    public void CastBuffForAgitManager(Creature talker, int reply) {
    }

    @AiEventId(235012432)
    public void SetDoorHpLevel(String door_name, int hp) {
    }

    @AiEventId(235012434)
    public void SetControlTowerLevel(String towerName, int level) {
    }

    @AiEventId(235012485)
    public void ShowSeedSetting(Creature talker, int state) {
    }

    @AiEventId(235012486)
    public void ShowCropSetting(Creature talker, int state) {
    }

    @AiEventId(234946792)
    public int Fortress_IsInBoundary(int param) {
        return 0;
    }

    @AiEventId(235208930)
    public void Fortress_OwnerRewardTaken(int unk0, int unk1, int fortress_id, int item_medal, int unk2) {
    }

    @AiEventId(234946694)
    public Creature Pledge_GetLeader(Creature talker) {
        return null;
    }

    @AiEventId(235012195)
    public void TeleportToUser(Creature talker, Creature user) {
    }

    @AiEventId(234881199)
    public String Castle_GetSiegeTime() {
        return null;
    }

    @AiEventId(235012415)
    public void TB_GetPledgeRegisterStatus(Creature talker, int status) {
    }

    @AiEventId(234946876)
    public void TB_RegisterMember(Creature talker) {
    }

    @AiEventId(234946877)
    public void TB_GetNpcType(Creature talker) {
    }

    @AiEventId(235012414)
    public void TB_SetNpcType(Creature talker, int type) {
    }

    @AiEventId(234946880)
    public void TB_GetBattleRoyalPledgeList(Creature talker) {
    }

    @AiEventId(235012213)
    public void RemoveMemo(Creature talker, int memo) {
    }

    @AiEventId(234946875)
    public void TB_RegisterPledge(Creature talker) {
    }

    @AiEventId(234946696)
    public Clan GetPledgeByIndex(int index) {
        return null;
    }

    @AiEventId(234881234)
    public int Agit_GetCostFailDay() {
        return 0;
    }

    @AiEventId(234946780)
    public void AuctionAgit_GetAgitCostInfo(Creature talker) {
    }

    @AiEventId(234881377)
    public int Lotto_GetState() {
        return 0;
    }

    @AiEventId(234881382)
    public int Lotto_GetTicketPrice() {
        return 0;
    }

    @AiEventId(234881064)
    public int GetLifeTime() {
        return 0;
    }

    @AiEventId(234881379)
    public int Lotto_GetRoundNumber() {
        return 0;
    }

    @AiEventId(234881388)
    public String Lotto_GetChosenNumber() {
        return null;
    }

    @AiEventId(234881383)
    public int Lotto_GetAccumulatedReward() {
        return 0;
    }

    @AiEventId(234946920)
    public void Lotto_MakeFinalRewardFHTML(FHTML fhtml) {
    }

    @AiEventId(235012457)
    public void Lotto_ShowPrevRewardPage(Creature talker, int reply) {
    }

    @AiEventId(235012458)
    public void Lotto_GiveReward(Creature talker, int reply) {
    }

    @AiEventId(235077988)
    public void Lotto_ShowBuyingPage(Creature talker, int reply, FHTML fhtml) {
    }

    @AiEventId(235077989)
    public void Lotto_BuyTicket(Creature talker, int reply, int unk1) {
    }

    @AiEventId(235012459)
    public void Lotto_ShowCurRewardPage(Creature talker, int reply) {
    }

    @AiEventId(234946621)
    public void LookNeighbor(int radius) {
    }

    @AiEventId(234946594)
    public void RemoveAttackDesire(int creature_objid) {
    }

    @AiEventId(234946825)
    public void UseSoulShot(int unk) {
    }

    @AiEventId(235077898)
    public void UseSpiritShot(int unk1, int spiritshot_speed_bonus, int spiritshot_heal_bonus) {
    }

    @AiEventId(234881057)
    public void RemoveAllAttackDesire() {
    }

    @AiEventId(235077694)
    public void BroadcastScriptEvent(int event, int event_arg, int radius) {
    }

    @AiEventId(235143232)
    public void BroadcastScriptEventCond(int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(235209350)
    public void BroadcastVoiceNPCEffect(Creature creature, String voiceFile, int unk0, int unk1, int izone_id) {
    }

    @AiEventId(234946603)
    public int GetDirection(Creature creature) {
        return 0;
    }

    @AiEventId(234881083)
    public int GetTimeHour() {
        return 0;
    }

    @AiEventId(234946975)
    public int GetAngleFromTarget(Creature target) {
        return 0;
    }

    @AiEventId(234946817)
    public int Skill_GetEffectPoint(int skill_name_id) {
        return 0;
    }

    @AiEventId(234881089)
    public int GetPathfindFailCount() {
        return 0;
    }

    @AiEventId(234946841)
    public void Suicide(int unk0) {
    }

    @AiEventId(234946627)
    public int CanAttack(Creature target) {
        return 0;
    }

    @AiEventId(235077670)
    public void MakeAttackEvent(Creature speller, double point, int unk1) {
    }

    @AiEventId(235209045)
    public void AddHateInfo(Creature target, int point, int unk1, int unk2, int unk3) {
    }

    @AiEventId(234946907)
    public HateInfo GetMaxHateInfo(int unk) {
        return null;
    }

    @AiEventId(234946561)
    public int IsNullHateInfo(HateInfo hateInfo) {
        return 0;
    }

    @AiEventId(235012447)
    public void RemoveAllHateInfoIF(int unk1, int unk2) {
    }

    @AiEventId(234881367)
    public int GetHateInfoCount() {
        return 0;
    }

    @AiEventId(234881559)
    public int IsMyBossAlive() {
        return 0;
    }

    @AiEventId(235012112)
    public void AddFollowDesire(Creature creater, double point) {
    }

    @AiEventId(235143198)
    public void AddMoveSuperPointDesire(String pointName, int pointMethod, double point, long unk) {
    }

    @AiEventId(235077663)
    public void AddMoveFreewayDesire(int freeway_id, int freewayMethod, double point) {
    }

    @AiEventId(234946828)
    public void CreatePrivates(String privates) {
    }

    @AiEventId(235012110)
    public void AddFleeDesire(Creature attacker, double point) {
    }

    @AiEventId(235143437)
    public void CreateOnePrivate(int npc_id, String npc_ai, int unk1, int unk2) {
    }

    @AiEventId(235209399)
    public void CreateOneAnother(int npc_id, String npc_ai, int x, int y, int z) {
    }

    @AiEventId(234881178)
    public Creature GetLastAttacker() {
        return null;
    }

    @AiEventId(234881550)
    public void InstantZone_MarkRestriction() {
    }

    @AiEventId(235143231)
    public void BroadcastScriptEventEx(int event_id, int script_event_arg1, int stript_event_arg2, int radius) {
    }

    @AiEventId(235012156)
    public void Summon_SetOption(int unk1, int unk2) {
    }

    @AiEventId(234946588)
    public void AddPetDefaultDesire_Follow(double unk1) {
    }

    @AiEventId(235339798)
    public void AddUseSkillDesireEx(int id, int skill, int unk1, int reply, int ask, double point, int unk2) {
    }

    @AiEventId(235470871)
    public void AddUseSkillDesireEx2(int id, int skill, int unk1, int reply, int ask, double point, int unk2, int unk3, int unk4) {
    }

    @AiEventId(235143197)
    public void AddMoveToTargetDesire(int target_id, int unk1, int unk2, double point) {
    }

    @AiEventId(234881073)
    public void StopMove() {
    }

    @AiEventId(235012206)
    public void ChangeStopType(int type, int unk) // time ?
    {
    }

    @AiEventId(234946612)
    public int IsStaticObjectID(int objecti_id) {
        return 0;
    }

    @AiEventId(234946615)
    public StaticObject GetStaticObjectFromID(int object_id) {
        return null;
    }

    @AiEventId(234946666)
    public int StaticObjectDistFromMe(StaticObject staticObject) {
        return 0;
    }

    @AiEventId(235143178)
    public void AddAttackDesireEx(int target_id, int unk1, int unk2, double point) {
    }

    @AiEventId(234881061)
    public void RandomizeAttackDesire() {
    }

    @AiEventId(235077910)
    public void EffectMusic(Creature object, int unk, String music) // unk is radius?
    {
    }

    @AiEventId(234881066)
    public int GetCurrentTick() {
        return 0;
    }

    @AiEventId(235077774)
    public void MPCC_SetMasterPartyRouting(int id, Creature creature, int unk) {
    }

    @AiEventId(234946701)
    public int MPCC_GetMPCCId(Creature talker) {
        return 0;
    }

    @AiEventId(234946697)
    public Creature MPCC_GetMaster(int mpcc_id) {
        return null;
    }

    @AiEventId(234946700)
    public int MPCC_GetMemberCount(int master_id) {
        return 0;
    }

    @AiEventId(235012355)
    public int Skill_HaveAttribute(int skill_name_id, int unk) {
        return 0;
    }

    @AiEventId(235077680)
    public void SetTeleportPosOnLost(int x, int y, int z) {
    }

    @AiEventId(234881488)
    public void RemoveAllDesire() {
    }

    @AiEventId(235536897)
    public void AddLogByNpc2(int unk0, Creature creature, String title, String msg, int unk1, int unk2, int unk3,
                             int unk4,
                             int unk5, int unk6) {
    }

    @AiEventId(234946618)
    public int GetGlobalMap(int map_id) {
        return 0;
    }

    @AiEventId(235012152)
    public void RegisterGlobalMap(int map_id, int unk) {
    }

    @AiEventId(234946617)
    public void UnregisterGlobalMap(int map_id) {
    }

    @AiEventId(235012221)
    public void SetDBValue(Creature npc, int db_value) {
    }

    @AiEventId(234946969)
    public void RegisterToEventListener(int unk) {
    }

    @AiEventId(234881418)
    public int GetSSQWinner() {
        return 0;
    }

    @AiEventId(234946968)
    public int IsJoinableToDawn(Creature talker) {
        return 0;
    }

    @AiEventId(235340064)
    public void EarthQuakeByNPC(Creature npc, int unk1, int unk2, int unk3, int unk4, int unk5, int unk6) {
    }

    @AiEventId(235077909)
    public void VoiceEffect(Creature talker, String voice, int unk) // duration?
    {
    }

    @AiEventId(235012394)
    public void ShowTutorialHTML(Creature talker, String html) {
    }

    @AiEventId(235012395)
    public void ShowNewTutorialHTML(Creature talker, String html) {
    }

    @AiEventId(235012169)
    public void ShoutEx(String msg, int unk) // radius ?
    {
    }

    @AiEventId(235078033)
    public int GetDepositedSSQItemCount(Creature talker, int ssq_type, int item_type) {
        return 0;
    }

    @AiEventId(235143572)
    public void DeleteDepositedSSQItem(Creature talker, int ssq_type, int item_type, int unk) {
    }

    @AiEventId(234881523)
    public int GetSSQPrevWinner() {
        return 0;
    }

    @AiEventId(235274639)
    public int AddSSQMember(Creature talker, int unk1, int unk2, int unk3, int unk4, int unk5) {
        return 0;
    }

    @AiEventId(234947018)
    public Creature FindNeighborHero(int radius) {
        return null;
    }

    @AiEventId(234946599)
    public int GetTopDesireValue(int unk) {
        return 0;
    }

    @AiEventId(235012400)
    public void EnableTutorialEvent(Creature talker, int mask) {
    }

    @AiEventId(234946863)
    public void CloseTutorialHTML(Creature talker) {
    }

    @AiEventId(235012397)
    public void ShowQuestionMark(Creature talker, int unk) {
    }

    @AiEventId(235143468)
    public void ShowTutorialHTML2(Creature talker, String html, int unk, String sound_effect) {
    }

    @AiEventId(235209009)
    public void ShowRadar(Creature talker, int x, int y, int z, int unk) {
    }

    @AiEventId(235077747)
    public void SetMemoState(Creature talker, int quest_id, int state) {
    }

    @AiEventId(235012622)
    public void SetNRMemo(Creature talker, int quest_id) {
    }

    @AiEventId(235143696)
    public void SetNRMemoStateEx(Creature talker, int quest_id, int unk1, int unk2) {
    }

    @AiEventId(235078163)
    public void SetNRFlagJournal(Creature talker, int quest_id, int flag) {
    }

    @AiEventId(234946827)
    public void SetPrivateID(int unk) {
    }

    @AiEventId(235339867)
    public void ShowSysMsgToParty2(Party party, int unk1, int unk2, int unk3, int unk4, int unk5, int unk6) {
    }

    @AiEventId(234946910)
    public void RemoveHateInfoByCreature(Creature creature) {
    }

    @AiEventId(235012625)
    public void RemoveNRMemo(Creature talker, int quest_id) {
    }

    @AiEventId(234947141)
    public int GetDominionSiegeID(Creature talker) {
        return 0;
    }

    @AiEventId(235274654)
    public void TeleportParty(int party_id, int x, int y, int z, int unk1, int unk2) {
    }

    @AiEventId(234946972)
    public int GetTimeAttackFee(int value) {
        return 0;
    }

    @AiEventId(234946637)
    public void EquipItem(int weapon) {
    }

    @AiEventId(235274643)
    public void DepositSSQItemEx(Creature talker, int ssq_type, int blue, int green, int red, int unk) {
    }

    @AiEventId(235012358)
    public void UseSkill(Creature target, int skill) {
    }

    @AiEventId(234881416)
    public int GetSSQRoundNumber() {
        return 0;
    }

    @AiEventId(235077777)
    public int OwnItemCountEx(Creature talker, int item_id, int unk) // count ?
    {
        return 0;
    }

    @AiEventId(234947022)
    public void SetEnchantOfWeapon(int value) {
    }

    @AiEventId(234946960)
    public int GetTimeOfSSQ(int unk) {
        return 0;
    }

    @AiEventId(235077720)
    public void BroadcastSystemMessage(Creature creature, int unk, int msg_id) {
    }

    @AiEventId(235077647)
    public void AddFleeDesireEx(Creature victim, int distance, double point) {
    }

    @AiEventId(234881483)
    public void RegisterAsOlympiadOperator() {
    }

    @AiEventId(234946998)
    public int GetOlympiadPoint(Creature talker) {
        return 0;
    }

    @AiEventId(234946999)
    public int IsMainClass(Creature talker) {
        return 0;
    }

    @AiEventId(234881459)
    public int GetOlympiadWaitingCount() {
        return 0;
    }

    @AiEventId(234881461)
    public int GetTeamOlympiadWaitingCount() {
        return 0;
    }

    @AiEventId(234881460)
    public int GetClassFreeOlympiadWaitingCount() {
        return 0;
    }

    @AiEventId(234946993)
    public void AddClassFreeOlympiad(Creature talker) {
    }

    @AiEventId(234947015)
    public void SetHero(Creature talker) {
    }

    @AiEventId(234946994)
    public void AddTeamOlympiad(Creature talker) {
    }

    @AiEventId(234946992)
    public void AddOlympiad(Creature talker) {
    }

    @AiEventId(234947005)
    public int GetStatusForOlympiadField(int unk) // class ?
    {
        return 0;
    }

    @AiEventId(234947007)
    public String GetPlayer1ForOlympiadField(int unk) // class ?
    {
        return null;
    }

    @AiEventId(234947008)
    public String GetPlayer2ForOlympiadField(int unk) // class ?
    {
        return null;
    }

    @AiEventId(234947063)
    public int GetOlympiadTradePoint(Creature talker) {
        return 0;
    }

    @AiEventId(234947034)
    public int IsWeaponEquippedInHand(Creature creature) {
        return 0;
    }

    @AiEventId(234947016)
    public int GetPreviousOlympiadPoint(Creature talker) {
        return 0;
    }

    @AiEventId(235012600)
    public void DeleteOlympiadTradePoint(Creature talker, int count) {
    }

    @AiEventId(235012538)
    public int GetRankByOlympiadRankOrder(int reply, int unk) {
        return 0;
    }

    @AiEventId(235012545)
    public void ObserveOlympiad(Creature talker, int value) {
    }

    @AiEventId(234947000)
    public void RemoveOlympiad(Creature talker) {
    }

    @AiEventId(235667746)
    public void SpecialCamera(Creature creature, int unk1, int unk2, int unk3, int unk4, int unk5, int unk6, int unk7,
                              int unk8, int unk9, int unk10, int unk11) {
    }

    @AiEventId(235667748)
    public void SpecialCamera3(Creature creature, int unk1, int unk2, int unk3, int duration, int unk4, int unk5,
                               int unk6,
                               int unk7, int unk8, int unk9, int unk10) {
    }

    @AiEventId(234947041)
    public void MG_GetUnreturnedPoint(Creature talker) {
    }

    @AiEventId(235012417)
    public void TB_CheckMemberRegisterStatus(int agit_id, Creature talker) {
    }

    @AiEventId(235012572)
    public void MG_RegisterPledge(Creature talker, int agit_war_cretificates_count) {
    }

    @AiEventId(234947037)
    public void MG_UnregisterPledge(Creature talker) {
    }

    @AiEventId(234947039)
    public void MG_JoinGame(Creature talker) {
    }

    @AiEventId(235077780)
    public void DropItem1(Creature creature, int item_id, int count) {
    }

    @AiEventId(234947025)
    public void ChangeMoveType(int new_type) {
    }

    @AiEventId(234881535)
    public void UnequipWeapon() {
    }

    @AiEventId(235078062)
    public void LookItem(int radius, int unk, int item_id) {
    }

    @AiEventId(235012606)
    public void PlayAnimation(int animation, int range) {
    }

    @AiEventId(234946698)
    public int MPCC_GetPartyCount(int mpcc_id) {
        return 0;
    }

    @AiEventId(235471362)
    public void GiveEventItem(Creature talker, int consume_item, int consume_count, int item_id, int count, int unk1,
                              int limit_time, int unk2, int unk3) {
    }

    @AiEventId(235143317)
    public void DropItem2(Creature creature, int item_id, int count, int attacker_id) {
    }

    @AiEventId(234881560)
    public int IsEventDropTime() {
        return 0;
    }

    @AiEventId(234947132)
    public void ShowPremiumItemList(Creature talker) {
    }

    @AiEventId(235078237)
    public int RegisterAsAirportManager(int airport_id, int platform_id, int less_than_100) {
        return 0;
    }

    @AiEventId(234947169)
    public int IsOccupiedPlatform(int id) {
        return 0;
    }

    @AiEventId(234947168)
    public void GetOnAirShip(Creature talker) {
    }

    @AiEventId(235078238)
    public void SummonAirShip(Creature talker, int airport_id, int platform_id) {
    }

    @AiEventId(235012108)
    public void AddGetItemDesireEx(int item_index, double point) {
    }

    @AiEventId(235143457)
    public void EarthQuakeToParty(int party_id, int unk1, int unk2, int unk3) {
    }

    @AiEventId(234946788)
    public int Fortress_GetState(int fortress_id) {
        return 0;
    }

    @AiEventId(235077868)
    public void Fortress_SetFacility(Creature talker, int unk1, int unk2) {
    }

    @AiEventId(235077853)
    public void Fortress_PledgeRegister(int creature_id, int talker_id, int fortress_id) {
    }

    @AiEventId(235077854)
    public void Fortress_PledgeUnregister(int creature_id, int talker_id, int fortress_id) {
    }

    @AiEventId(234947040)
    public void MG_SetWinner(Creature creature) {
    }

    @AiEventId(235012703)
    public void BuyAirShip(Creature talker, int unk) {
    }

    @AiEventId(234947078)
    public void SetVisible(int value) {
    }

    @AiEventId(235012611)
    public void SetWorldTrapVisibleByClassId(int class_id, int value) {
    }

    @AiEventId(235012612)
    public void ActivateWorldTrapByClassId(int creature_id, int class_id) {
    }

    @AiEventId(235012613)
    public void DefuseWorldTrapByClassId(int creature_id, int class_id) {
    }

    @AiEventId(234881544)
    public int InstantZone_GetId() {
        return 0;
    }

    @AiEventId(235143700)
    public void SetStaticMeshStatus(Creature creature, String mesh_name, int targetable, int mesh_index) {
    }

    @AiEventId(235012235)
    public int MPCC_GetPartyID(int mppc_id, int party_index) {
        return 0;
    }

    @AiEventId(235471135)
    public void InstantTeleportMPCC(Creature talker, int x, int y, int z, int unk1, int unk2, int unk3, int unk4,
                                    int unk5) {
    }

    @AiEventId(234881065)
    public int GetTick() {
        return 0;
    }

    @AiEventId(234947068)
    public void ShowBaseAttributeCancelWindow(Creature talker) {
    }

    @AiEventId(235143393)
    public void Fortress_ContractCastle(int creature_id, int talker_id, int fortress_id, int castle_id) {
    }

    @AiEventId(235077714)
    public void BroadcastSystemMessageStr(Creature creature, int radius, String msg) {
    }

    @AiEventId(235405402)
    public void BroadcastSystemMessageFStr(Creature creature, int radius, int stringId, String msg0, String msg, String unk0, String unk1, String unk2) {
        // TODO
    }

    @AiEventId(234946797)
    public void Fortress_ResetFacility(Creature talker) {
    }

    @AiEventId(234946790)
    public int Fortress_GetOwnerRewardCycleCount(int fortress_id) {
        return 0;
    }

    @AiEventId(235208910)
    public void Fortress_GetOwnerRewardCycleCount(int creature_id, int talker_id, int fortress_id, int item_medal,
                                                  int count) {
        // TODO
    }

    @AiEventId(234946791)
    public int Fortress_GetCastleTreasureLevel(int fortress_id) {
        return 0;
    }

    @AiEventId(235077859)
    public void Fortress_CastleTreasureTaken(int creature_id, int talker_id, int fortress_id) {
    }

    @AiEventId(234946795)
    public int Fortress_GetPledgeSiegeState(Creature creature) {
        return 0;
    }

    @AiEventId(235012320)
    public void Fortress_ProtectedNpcDied(int creature_id, int fortress_id) {
    }

    @AiEventId(235077855)
    public void Fortress_BarrackCaptured(int creature_id, int fortress_id, int barrack_id) {
    }

    @AiEventId(235274526)
    public void InstantTeleportInMyTerritoryWithCondition(int x, int y, int z, int unk1, int unk2, int unk3) {
    }

    @AiEventId(235012643)
    public void RegisterResurrectionTower(int unk1, int unk2) {
    }

    @AiEventId(234947100)
    public void CreatePVPMatch(int n_type) {
    }

    @AiEventId(235012642)
    public void CheckRegisterParty2(Party party1, Party party2) {
    }

    @AiEventId(235208731)
    public void AddEffectActionDesire2(Creature creature, int effect_action, int unk1, int unk2, int unk3) {
    }

    @AiEventId(234946900)
    public void SetMaxHateListSize(int max_size) {
    }

    @AiEventId(234946904)
    public HateInfo GetHateInfoByIndex(int index) {
        return null;
    }

    @AiEventId(234947134)
    public int IsStackableItemEx(int item_index) {
        return 0;
    }

    @AiEventId(234947116)
    public void RegisterUserResurrectionTower(int creature_id) {
    }

    @AiEventId(234947118)
    public void IsUserPVPMatching(Creature creature) {
    }

    @AiEventId(235012657)
    public void AddKillPointUserPVPMatch(Creature c0, int point) {
    }

    @AiEventId(234947117)
    public void CheckRegisterUserPVPMatch(Creature talker) {
    }

    @AiEventId(234947120)
    public void UnregisterUserPVPMatch(Creature talker) {
    }

    @AiEventId(234947119)
    public void RegisterUserPVPMatch(Creature talker) {
    }

    @AiEventId(235405344)
    public void AddMoveFormationDesire(String unk, int unk1, int group_id, int group_id_, int fromation_id, int unk2,
                                       int unk3, double point) {
    }

    @AiEventId(234947147)
    public void BlockUpsetManagerEnter(int group_id) {
    }

    @AiEventId(235012685)
    public void BlockUpsetUserEnter(int group_id, Creature talker) {
    }

    @AiEventId(235012687)
    public void BlockUpsetChangeAmount(int group_id, int amount) {
    }

    @AiEventId(234947150)
    public void BlockUpsetRegisterMe(int group_id) {
    }

    @AiEventId(235012688)
    public void BlockUpsetChangeColor(int ground_id, int color_id) {
    }

    @AiEventId(234881621)
    public int GetEvolutionId() {
        return 0;
    }

    @AiEventId(235143756)
    public void BlockUpset(int ground_id, int unk, Creature creature, int block_upset_point) {
    }

    @AiEventId(235078232)
    public void SetDieEvent(Creature target, int unk1, int unk2) {
    }

    @AiEventId(235077790)
    public void FHTML_SetStr(FHTML fhtml, String find, String replacement) {
    }

    @AiEventId(235405471)
    public void FHTML_SetFStr(FHTML fhtml, String find, int replacement, String msg0, String msg1, String msg2,
                              String msg3, String msg4) {
    }

    @AiEventId(234881296)
    public int Maker_GetNpcCount() {
        return 0;
    }

    @AiEventId(234947123)
    public int GetOverhitBonus(Creature creature) {
        return 0;
    }

    @AiEventId(234947122)
    public void GetRankUserPVPMatch(Creature creature) {
    }

    @AiEventId(235209308)
    public void StartScenePlayerAround(Creature creature, int scene, int radius, int min_z, int max_z) {
    }

    @AiEventId(235012722)
    public void TeleportTo(Creature from, Creature to) {
    }

    @AiEventId(235078231)
    public void RenewSpawnedPos(int x, int y, int z) {
    }

    @AiEventId(234947137)
    public void ChangeStatus(int status) {
    }

    @AiEventId(235012638)
    public void UnregisterPVPMatch(Party party, Creature creature) {
    }

    @AiEventId(235012639)
    public void StartPVPMatch(Party party1, Party party2) {
    }

    @AiEventId(235012637)
    public void RegisterPVPMatch(Party party, Creature talker) {
    }

    @AiEventId(234946905)
    public HateInfo GetHateInfoByCreature(Creature creature) {
        return null;
    }

    @AiEventId(234881617)
    public void CleftManagerEnter() {
    }

    @AiEventId(234881618)
    public int GetCleftState() {
        return 0;
    }

    @AiEventId(234947155)
    public void CleftUserEnter(Creature talker) {
    }

    @AiEventId(235012651)
    public void IsToggleSkillOnOff(Creature creature, int skill_id) {
    }

    @AiEventId(235078228)
    public void CleftCenterDestroyed(int zone_type, Creature creature, int destroy_point) {
    }


    @AiEventId(235274337)
    public void InstantTeleportWithItem(Creature creature, int x, int y, int z, int item, int item_count) {
    }

    @AiEventId(234881636)
    public void EventManagerEnter() {
    }

    @AiEventId(235143311)
    public void GiveItemEx(Creature talker, int item_id, int count, int unk) {
    }

    @AiEventId(235208721)
    public void AddFollowDesire2(Creature creature, int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(234947174)
    public void SetAbilityItemDrop(int ability) {
    }

    @AiEventId(235077637)
    public void AddMoveAroundLimitedDesire(int unk1, int unk2, double point) {
    }

    @AiEventId(235012698)
    public void StartScenePlayer(Creature talker, int scene) {
    }

    @AiEventId(234947170)
    public int IsCleftUser(Creature talker) {
        return 0;
    }

    @AiEventId(235012713)
    public void ObserveEventMatch(Creature talker, int unk) {
    }

    @AiEventId(234947087)
    public void InstantZone_AddExtraDuration(int duration) {
    }

    @AiEventId(234946912)
    public void SetHateInfoListIndex(int index) {
    }

    @AiEventId(235078268)
    public void ChangeDir(Creature creature, int creature_id,
                          int direction) // direction to creature_id, or to direction
    {
    }

    @AiEventId(235012724)
    public void ChangeNPCState(Creature creature, int state) {
    }

    @AiEventId(234881646)
    public void RegisterAsFieldCycleManager() {
    }

    @AiEventId(235012731)
    public void SetAttackable(Creature creature, int value) {
    }

    @AiEventId(234947194)
    public int IsAttackable(Creature creature) {
        return 0;
    }

    @AiEventId(234947178)
    public void BlockTimer(int timer_id) {
    }

    @AiEventId(234947179)
    public void UnblockTimer(int timer_id) {
    }

    @AiEventId(235667727)
    public void CreateOnePrivateInzoneEx(int npc_class_id, String ai, int weight_point, int unk1, int x, int y, int z,
                                         int angle, int master_id, int unk2, int unk3, int instance_zone_id) {
    }

    @AiEventId(234946844)
    public int IsInThisTerritory(String area_name) {
        return 0;
    }

    @AiEventId(234947023)
    public void RemoveDesire(int unk) {
    }

    @AiEventId(235012726)
    public void ChangeNickName(Creature sm, String nickname) {
    }

    @AiEventId(235078263)
    public void ChangeFStrNickName(Creature sm, int fstrId, String nickname) {
    }

    @AiEventId(235012728)
    public void ChangeMasterName(Creature sm, String mastername) {
    }

    @AiEventId(235078265)
    public void ChangeFStrMasterName(Creature sm, int fstrId, String mastername) {
    }

    @AiEventId(234947181)
    public void FixMoveType(int type) {
    }

    @AiEventId(235012562)
    public void ChangeMoveType2(int unk1, int unk2) {
    }

    @AiEventId(234947203)
    public void ChangeUserTalkTarget(Creature creature) {
    }

    @AiEventId(234881060)
    public void RemoveAbsoluteDesire() {
    }

    @AiEventId(235078277)
    public void VoiceNPCEffect(Creature creature, String voice, int unk) {
    }

    @AiEventId(235209342)
    public void IncreaseNPCLogByID(Creature target, int unk1, int unk2, int npc_class_id, int value) {
    }

    @AiEventId(235143808)
    public int GetNPCLogByID(Creature target, int unk1, int unk2, int npc_class_id) {
        return 0;
    }

    @AiEventId(235077981)
    public HateInfo GetNthHateInfo(int unk1, int hate_index, int unk2) {
        return null;
    }

    @AiEventId(235078261)
    public void ChangeZoneInfo(Creature creature, int unk1, int unk2) {
    }

    @AiEventId(235143812)
    public void FindRandomUser(int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(235340426)
    public void CreateOnePrivateNearUser(Creature talker, int unk1, String ai, int weight_point, int unk2, int unk3,
                                         int unk4) {
    }

    @AiEventId(234881673)
    public void GetPlayingUserCount() {
    }

    @AiEventId(234881675)
    public Creature GetMasterUser() {
        return null;
    }

    @AiEventId(234946634)
    public void SayInt(int value) {
    }

    @AiEventId(235078256)
    public void GetEventRankerInfo(int event_id, Creature talker, int unk) {
    }

    @AiEventId(235078257)
    public void DecreaseEventTopRankerReward(int event_id, int unk, Creature talker) {
    }

    @AiEventId(235078255)
    public void InsertEventRanking(int eventData, Creature creature, int unk) {
    }

    @AiEventId(235012748)
    public void CanGiveEventData(Creature talker, int eventData) {
    }

    @AiEventId(235077943)
    public void DestroyPet(Creature talker, int dbid, int petlevel) {
    }

    @AiEventId(218169350)
    public void RegisterAgitSiegeEventEx(int castle_id) {
    }

    @AiEventId(234946681)
    public void SetCurrentQuestID(int quest_id) {
    }

    @AiEventId(235077742)
    public void SetJournal(Creature target, int quest, int unk) {
    }

    @AiEventId(234947048)
    public int HasAcademyMaster(Creature target) {
        return 0;
    }

    @AiEventId(234947050)
    public Creature GetAcademyMaster(Creature target) {
        return null;
    }

    @AiEventId(234946811)
    public Creature Maker_FindNpcByKey(int key) {
        return null;
    }

    @AiEventId(235209010)
    public void DeleteRadar(Creature talker, int x, int y, int z, int unk) {
    }

    @AiEventId(235012334)
    public void AddChoice(int choice, int unk) {
    }

    @AiEventId(235012335)
    public void ShowChoicePage(Creature talker, int unk) {
    }

    @AiEventId(234947049)
    public int HasAcademyMember(Creature talker) {
        return 0;
    }

    @AiEventId(234947051)
    public Creature GetAcademyMember(Creature talker) {
        return null;
    }

    @AiEventId(235078061)
    public int GetHTMLCookie(Creature talker, int quest_id, int unk) {
        return 0;
    }

    @AiEventId(235077716)
    public void ShowQuestPage(Creature talker, String html, int quest_id) {
    }

    @AiEventId(235012403)
    public void DeleteAllRadar(Creature talker, int unk) {
    }

    @AiEventId(234946673)
    public int GetMemoCount(Creature talker) {
        return 0;
    }

    @AiEventId(235077793)
    public void ShowQuestFHTML(Creature talker, FHTML fhtml, int quest_id) {
    }

    @AiEventId(234947107)
    public int IsHostileInDominionSiege(Creature creature) {
        return 0;
    }

    @AiEventId(235012210)
    public void SetMemo(Creature talker, int quest_id) {
    }

    @AiEventId(235078060)
    public void SetHTMLCookie(Creature talker, int quest_id, int coockie) {
    }

    @AiEventId(234946983)
    public void ClearBingoBoard(Creature talker) {
    }

    @AiEventId(235012512)
    public void CreateBingoBoard(Creature talker, int unk) {
    }

    @AiEventId(235012515)
    public void SelectBingoNumber(Creature talker, int number) {
    }

    @AiEventId(235012514)
    public int GetNumberFromBingoBoard(Creature talker, int unk) {
        return 0;
    }

    @AiEventId(235012517)
    public int IsSelectedBingoNumber(Creature talker, int number) {
        return 0;
    }

    @AiEventId(234946980)
    public int GetBingoSelectCount(Creature talker) {
        return 0;
    }

    @AiEventId(234946982)
    public int GetMatchedBingoLineCount(Creature talker) {
        return 0;
    }

    @AiEventId(235078043)
    public void AddTimeAttackFee(int unk1, int unk2, int party_id) {
    }

    @AiEventId(235274650)
    public void AddTimeAttackRecord(int unk1, int SSQ_type, int party_id, int item_count, int time_of_day,
                                    int memo_state) {
    }

    @AiEventId(235143581)
    public void GiveTimeAttackReward(Creature talker, int unk1, int tem_id, int unk2) {
    }

    @AiEventId(235012636)
    public void DeclareLord(int dominion, Creature talker) {
    }

    @AiEventId(234946888)
    public int IsDualClass(Creature talker) {
        return 0;
    }

    @AiEventId(234946886)
    public int HasSubJobClass(Creature talker) {
        return 0;
    }

    @AiEventId(234946889)
    public int GetMainClassID(Creature talker) {
        return 0;
    }

    @AiEventId(235077763)
    public int GetQuestItemProb(Creature talker, Party party, int level) {
        return 0;
    }

    @AiEventId(234947215)
    public Creature GetPet(Creature talker) {
        return null;
    }

    @AiEventId(234947216)
    public int HaveSummonOrPet(Creature talker) {
        return 0;
    }

    @AiEventId(235012793)
    public void SetTargetUserLevel(Creature talker, int level) {
    }

    @AiEventId(235078307)
    public void CallToChangeClass(Creature talker, int classId, int unk) {
    }

    @AiEventId(235012772)
    public void ChangeClassAndSetSkill(Creature talker, int reply) {
    }

    @AiEventId(234947010)
    public int IsOlympiadRegistered(Creature talker) {
        return 0;
    }

    @AiEventId(234881475)
    public int GetOlympiadSeason() {
        return 0;
    }

    @AiEventId(234881476)
    public int GetOlympiadStep() {
        return 0;
    }

    @AiEventId(234881477)
    public int GetOlympiadPlayerCount() {
        return 0;
    }

    @AiEventId(234881684)
    public void GetAllUserForInZone() {
    }

    @AiEventId(235012357)
    public void SetSkill(Creature talker, int skill_hash) {
    }

    @AiEventId(234947256)
    public void ShowStatPage(Creature talker) {
    }

    @AiEventId(234947227)
    public void InstantAgit_Enter(Creature talker) {
    }

    @AiEventId(234947229)
    public void InstantAgit_Expel(Creature talker) {
    }

    @AiEventId(234881698)
    public void InstantAgit_EnterManager() {
    }

    @AiEventId(235012760)
    public void InstantAgit_Apply(Creature talker, int unk) {
    }

    @AiEventId(235012761)
    public void InstantAgit_AddDeco(int reply, int unk) {
    }

    @AiEventId(235078305)
    public int InstantAgit_GetDecoData(Creature talker, int type, int unk0) {
        return 0;
    }

    @AiEventId(235012766)
    public int InstantAgit_GetApplyCount(int unk0, int unk1) {
        return 0;
    }

    @AiEventId(235012767)
    public int InstantAgit_GetApplyFirstDate(int group, int unk) {
        return 0;
    }

    @AiEventId(235012768)
    public int InstantAgit_GetApplyLastDate(int group, int unk) {
        return 0;
    }

    @AiEventId(235078286)
    public void ShowChannelingEffect(Creature talker, int id, int unk) {
    }

    @AiEventId(234946642)
    public void SetSuperPointSize(int size) {
    }

    @AiEventId(235012755)
    public int GetAIParameter(Creature talker, int param) {
        return 0;
    }

    @AiEventId(235078290)
    public void SetAIParameter(Creature talker, int unk0, int unk1) {
    }

    @AiEventId(235143848)
    public void SendUnionGameResult(Creature creature, int unk0, int db_id, int unk1) {
    }

    @AiEventId(235012248)
    public void GiveItemViaPledgeGame(Creature talker, int item_id) {
    }

    @AiEventId(234881677)
    public int GetRewardNpcType() {
        return 0;
    }

    @AiEventId(235012786)
    public void SetCompeteAgitOwner(Creature talker, int unk0) {
    }

    @AiEventId(235143847)
    public void ShowPledgeUnionState(Creature talker, int unk0, int unk1, int unk2) {
    }

    @AiEventId(235078313)
    public void SummonPledgeMember(Creature master, int unk0, int unk1) {
    }

    @AiEventId(235078259)
    public void TeleportToInstantZone(Creature creature, Creature creature0, int unk) {
    }

    @AiEventId(235012774)
    public void ChangeToAwakenedClass(Creature talker, int class_id) {
    }

    @AiEventId(234947247)
    public void RequestTotalSkillEnchantCount(Creature talker) {
    }

    @AiEventId(235012782)
    public void GiveRewardBySkillEnchant(Creature talker, int unk0) {
    }

    @AiEventId(268566697)
    public void SetSkillAllByEtcClass() {
    }

    @AiEventId(235077783)
    public void DeleteQuestItem(Creature talker, int item_id, int item_count) {
    }

    @AiEventId(234947237)
    public void ShowCommission(Creature talker) {
    }

    @AiEventId(235012797)
    public int CheckAndSetOneTimeUserFlag(int quest_id, Creature talker) {
        return 0;
    }

    @AiEventId(235012784)
    public void CheckWeightToChangeSubclass(Creature talker, int unk0) {
    }

    @AiEventId(234947260)
    public int IsUserWithPrivateStore(Creature talker) {
        return 0;
    }

    @AiEventId(235078316)
    public void SRandNumbers(int i1, int i2, int i3) {
    }

    @AiEventId(234947245)
    public int SRandGetNumbers(int unk0) {
        return 0;
    }

    @AiEventId(234947242)
    public int GetUserInkEnergy(Creature talker) {
        return 0;
    }

    @AiEventId(235078315)
    public void UpdateUserInkEnergy(Creature talker, int unk0, int unk1) {
    }
}