package org.l2junity.gameserver.retail.model;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.retail.AiEventId;
import org.l2junity.gameserver.retail.model.actor.Creature;
import org.l2junity.gameserver.retail.model.actor.Npc;
import org.l2junity.gameserver.retail.model.actor.NpcMaker;
import org.l2junity.gameserver.retail.model.formation.Party;

/***
 * @author ANZO, Camellion
 * @since 20.03.2017
 */
public class GlobalObject {
    @AiEventId(268566568)
    public int GetAbnormalLevel(Creature creature, int abnormal_type) {
        return 0;
    }

    @AiEventId(268500999)
    public int Rand(int max) {
        return Rnd.get(max);
    }

    @AiEventId(268501000)
    public int RandOrPreset(int max) {
        // TODO
        return Rnd.get(max);
    }

    @AiEventId(268566583)
    public int OwnItemCount(Creature creature, int itemId) {
        return 0;
    }

    @AiEventId(268501027)
    public int FloatToInt(double value) {
        return (int) Math.round(value);
    }

    @AiEventId(268828716)
    public String MakeFString(int fstring, String s0, String s1, String s2, String s3, String s4) {
        return "";
    }

    @AiEventId(268501063)
    public void SetAsNull(Creature c) {
    }

    @AiEventId(268501021)
    public Party GetParty(Creature c0) {
        return null;
    }

    @AiEventId(268566585)
    public int HaveMemo(Creature creature, int quest) {
        return 0;
    }

    @AiEventId(268501052)
    public Creature Party_GetLeader(Creature talker) {
        return null;
    }

    @AiEventId(268501042)
    public int Party_GetCount(Creature talker) {
        return 0;
    }

    @AiEventId(268566587)
    public Creature Party_GetCreature(Creature creature, int index) {
        return null;
    }

    @AiEventId(268501082)
    public int Fortress_GetContractStatus(int fortress_id) {
        return 0;
    }

    @AiEventId(268501026)
    public int Castle_GetDomainFortressContractStatus(int fortress_id) {
        return 0;
    }

    @AiEventId(268566553)
    public boolean IsSameString(String str1, String str2) {
        return str1.equals(str2);
    }

    @AiEventId(268566586)
    public int GetMemoState(Creature talker, int quest) {
        return 0;
    }

    @AiEventId(268501014)
    public boolean IsNull(Object obj) {
        return obj == null;
    }

    @AiEventId(268501039)
    public String IntToStr(int value) {
        return String.valueOf(value);
    }

    @AiEventId(268566597)
    public String GetSubpledgeMasterName(Creature talker, int reply) {
        return "";
    }

    @AiEventId(268435528)
    public Creature GetNullCreature() {
        return null;
    }

    @AiEventId(268501122)
    public void SetSkillAll(Creature talker) {
    }

    @AiEventId(268697627)
    public void AddLogEx(int unk1, Creature talker, int unk2, int unk3) {
    }

    @AiEventId(268566638)
    public boolean GetDailyQuestFlag(Creature talker, int quest_id) {
        return false;
    }

    @AiEventId(268566639)
    public void SetDailyQuestFlag(Creature talker, int quest_id) {
    }

    @AiEventId(268632120)
    public int GetMemoStateEx(Creature talker, int quest_id, int unk) {
        return 0;
    }

    @AiEventId(268501098)
    public boolean IsCreateDate(Creature talker) {
        return false;
    }

    @AiEventId(268501099)
    public boolean CanGetBirthdayGift(Creature talker) {
        return false;
    }

    @AiEventId(268566628)
    public int GetNRMemoState(Creature talker, int mission) {
        return 0;
    }

    @AiEventId(269090819)
    public void ShowOnScreenMsgStr(Creature talker, int unk1, int unk2, int unk3, int unk4, int unk5, int unk6,
                                   int unk7,
                                   int unk8, String str) {
    }

    @AiEventId(269418500)
    public void ShowOnScreenMsgFStr(Creature talker, int unk0, int unk1, int unk2, int unk3, int unk4, int unk5, int unk6,
                                    int param0, int param1, String param2, String param3, String param4, String param5, String param6) {
    }

    @AiEventId(268501125)
    public int GetDominionWarState(int dominion_id) {
        return 0;
    }

    @AiEventId(235012622)
    public void SetNRMemo(Creature talker, int unk1) {
    }

    @AiEventId(235078198)
    public void SetNRMemoState(Creature talker, int quest_id, int state) {
    }

    @AiEventId(268501044)
    public int GetSSQPart(Creature talker) {
        return 0;
    }

    @AiEventId(268566549)
    public int GetDateTime(int value, int dtf) {
        return 0;
    }

    @AiEventId(268566560)
    public void Castle_GateOpenClose2(String door_name, int close) {
    }

    @AiEventId(268632115)
    public void AddLog(int unk1, Creature talker, int unk2) {
    }

    @AiEventId(268501034)
    public Creature GetCreatureFromID(int objId) {
        return null;
    }

    @AiEventId(268501036)
    public int GetIndexFromCreature(Creature creature) {
        return 0;
    }

    @AiEventId(268501035)
    public Creature GetCreatureFromIndex(int index) {
        return null;
    }

    @AiEventId(268501066)
    public int GetStep_FieldCycle(int field_cycle) {
        return 0;
    }

    @AiEventId(268501067)
    public int GetPoint_FieldCycle(int field_cycle) {
        return 0;
    }

    @AiEventId(268697676)
    public void AddPoint_FieldCycle(int field_cycle, int field_cycle_quantity, int unk, Creature creature) {
    }

    @AiEventId(268697678)
    public void SetStep_FieldCycle(int field_cycle, int unk1, int unk2, Creature creature) {
    }

    @AiEventId(268632143)
    public void SetStepWithoutActor_FieldCycle(int field_cycle, int unk1, int unk2) {
    }

    @AiEventId(268501029)
    public int Skill_IsMagic(int skill_id) {
        return 0;
    }

    @AiEventId(269156357)
    public void BroadcastOnScreenMsgStr(Creature creature, int radius, int type, int unk1, int unk2, int unk3, int unk4,
                                        int unk5, int time, int unk7, String msg) {
    }

    @AiEventId(269484038)
    public void BroadcastOnScreenMsgFStr(Creature creature, int radius, int type, int unk1, int unk2, int unk3, int unk4,
                                         int unk5, int time, int unk7, int unk8, String unk9, String unk10, String unk11,
                                         String unk12, String unk13) {
    }

    @AiEventId(269484195)
    public void BroadcastUIEventFStr(Creature creature, int radius, int type, int unk1, int unk2, String unk9, String unk10, String unk11,
                                     String unk12, String unk13, int unk3, String unk14, String unk15, String unk16,
                                     String unk17, String unk18) {
    }

    @AiEventId(269484196)
    public void BroadcastUIEventFStrInTerritory(int inzone_id, String maker_name, int unk0, int unk1, int unk2, String unk3, String unk4, String unk5, String unk6, String unk7, int fstringId, String unk8, String unk9, String unk10, String unk11, String unk12) {
    }

    @AiEventId(268632087)
    public void SendScriptEvent(Creature target, int event, int script_event_arg1) {
    }

    @AiEventId(268763162)
    public void InstantTeleportEx(Creature creature, int x, int y, int z, int unk) // direction?
    {
    }

    @AiEventId(268566655)
    public void LoadDBSavingMap(Creature creature, int map_id) {
    }

    @AiEventId(268632127)
    public void ClearEventRoom(int room_index, int party_type, int flag) {
    }

    @AiEventId(268697616)
    public void SendMakerScriptEvent(NpcMaker maker, int event, int script_event_arg1, int script_event_arg2) {
    }

    @AiEventId(268501003)
    public NpcMaker GetNpcMaker(String maker_name) {
        return null;
    }

    @AiEventId(268566652)
    public void RegisterDBSavingMap(int map_id, int unk) {
    }

    @AiEventId(268566573)
    public Party GetPartyFromEventRoom(int room_index, int ssq_part) {
        return null;
    }

    @AiEventId(268632152)
    public int GetNRMemoStateEx(Creature talker, int quest_id, int unk) {
        return 0;
    }

    @AiEventId(268501118)
    public int GetDBSavingMap(int map_id) {
        return 0;
    }

    @AiEventId(268697649)
    public String GetTimeAttackRecordInfo(int unk1, int unk2, int unk3, int unk4) {
        return null; // int compatiable string
    }

    @AiEventId(268501053)
    public int StrToInt(String str) {
        return Integer.parseInt(str);
    }

    @AiEventId(268435466)
    public int GetTimeOfDay() {
        return 0;
    }

    @AiEventId(268566559)
    public void Area_SetOnOff(String area_name, int value) // 0 = off, 1 = on
    {
    }

    @AiEventId(268501007)
    public Npc GetNPCFromID(int npc_id) {
        return null;
    }

    @AiEventId(268566545)
    public int CreateRoomInfoList(String level_name, int unk) {
        return 0;
    }

    @AiEventId(268632129)
    public void SetNpcParam(Creature npc, int param, Object value) {
    }

    @AiEventId(268501010)
    public RoomInfoList GetRoomInfoList(String level_name) {
        return null;
    }

    @AiEventId(268632175)
    public void SendDominionScriptEvent(int creature_index, int dominion_id, int event) {
    }

    @AiEventId(268501011)
    public void Announce(String msg) {
    }

    @AiEventId(268828692)
    public void AnnounceFStr(int fstr, String s0, String s1, String s2, String s3, String s4) {
    }

    @AiEventId(268501038)
    public Party GetPartyFromID(int id) {
        return null;
    }

    @AiEventId(268566631)
    public int OwnItemEnchantCount(Creature talker, int item_id) {
        return 0;
    }

    @AiEventId(268501090)
    public int GetBirthdayGiftCount(Creature talker) {
        return 0;
    }

    @AiEventId(268501089)
    public void SaveGetBirthdayGiftTime(Creature talker) {
    }

    @AiEventId(268501107)
    public boolean CanGet5YearGift(Creature talker) {
        return false;
    }

    @AiEventId(268501109)
    public void SaveGet5YearGiftTimeCount(Creature talker) {
    }

    @AiEventId(268501001)
    public int GetL2Time(int unk) {
        return 0;
    }

    @AiEventId(268501079)
    public int Fortress_GetOwnerPledgeId(int fortress_id) {
        return 0;
    }

    @AiEventId(268566617)
    public int Fortress_GetFacilityLevel(int fortress_id, int unk1) {
        return 0;
    }

    @AiEventId(268632097)
    public void Castle_GateOpenCloseEx(String door_name, int value, int instant_zone_id) {
    }

    @AiEventId(268632158)
    public void Area_SetOnOffEx(String area_name, int value, int instant_zone_id) {
    }

    @AiEventId(268697624)
    public void SendScriptEventEx(Creature creature, int event_id, int script_event_arg1, int script_event_arg2) {
    }

    @AiEventId(268566540)
    public NpcMaker InstantZone_GetNpcMaker(int instant_zone_id, String maker_name) {
        return null;
    }

    @AiEventId(268697695)
    public void Area_SetBannedTerritoryOnOff(String area_name, int value, int unk, int instant_zone_id) {
    }

    @AiEventId(268501080)
    public int Fortress_GetParentCastleId(int fortress_id) {
        return 0;
    }

    @AiEventId(268501084)
    public int Fortress_GetRentCost(int fortress_id) {
        return 0;
    }

    @AiEventId(268501085)
    public int Fortress_GetNextRewardRemainTime(int fortress_id) {
        return 0;
    }

    @AiEventId(268501083)
    public int Fortress_GetAvailableOwnMinutes(int fortress_id) {
        return 0;
    }

    @AiEventId(268566630)
    public String GetRank_RimKamaroka(int unk1, int unk2) {
        return "";
    }

    @AiEventId(268632165)
    public void SetPoint_RimKamaroka(Creature talker, int point, int instance_zone_id) {
    }

    @AiEventId(268632092)
    public void AddLogExWithoutCreature(int unk1, int unk2, int unk3) {
    }

    @AiEventId(268566569)
    public int GetAbnormalMagicLevel(Creature creature, int abnormal_type) {
        return 0;
    }

    @AiEventId(268566650)
    public void InzoneDoorBreakable(String door_name, int instance_zone_id) {
    }

    @AiEventId(269418601)
    public void SendUIEventFStr(Creature creature, int unk1, int unk2, int unk3, String unk4, String unk5, String unk6,
                                String unk7, String unk8, int fstringId, String unk10, String unk11, String unk12, String unk13, String unk14) {
    }

    @AiEventId(268566659)
    public void SetXMasEventState(int skill, int unk) {
    }

    @AiEventId(268632198)
    public void ShowMsgInTerritory(int unk, String my_trr, int msg) {
    }

    @AiEventId(268959880)
    public void ShowFStrMsgInTerritory2(int id, String fstring, int fstrId, String param0, String param1,
                                        String param2, String param3, String param4) {
    }

    @AiEventId(268632202)
    public Point GetRandomPosInCreature(Creature creature, int min_distance, int max_distance) {
        return null;
    }

    @AiEventId(268566665)
    public Point GetRandomPosInTerritory(String area_name, int unk) {
        return null;
    }

    @AiEventId(268828811)
    public Point GetRandomPosInPos(int x, int y, int z, int unk, int unk0, int radius) {
        return null;
    }

    @AiEventId(268501139)
    public void LoadJackpotTime(int time) {
    }

    @AiEventId(268501140)
    public boolean IsJackpotTime(int date) {
        return false;
    }

    @AiEventId(268435605)
    public int GetPlayingUserAverage() {
        return 0;
    }

    @AiEventId(268435608)
    public int GetEventElapsedTime() {
        return 0;
    }

    @AiEventId(268566670)
    public void SetEventValue(int key, int value) {
    }

    @AiEventId(268566681)
    public void SetEventDebugInfo(int unk1, int unk2) {
    }

    @AiEventId(268501133)
    public int GetEventValue(int key) {
        return 0;
    }

    @AiEventId(268697750)
    public void StartBuffEvent(int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(269025431)
    public void SetEventState(int date, int unk1, int unk2, int unk3, int unk4, int unk5, int unk6, String str1,
                              String str2) {
    }

    @AiEventId(268632141)
    public void AddPointWithoutActor_FieldCycle(int field_cycle, int state, int point) {
    }

    @AiEventId(268566625)
    public void AddScriptLog(int log, String str) {
    }

    @AiEventId(268501121)
    public int GetDominionState(int dominion_id) {
        return 0;
    }

    @AiEventId(268435469)
    public CodeInfoList AllocCodeInfoList() {
        return null;
    }

    @AiEventId(268566598)
    public void AddPCSocial(int creature_index, int unk) {
    }

    @AiEventId(268632126)
    public void AddPartyToEventRoom(int event_room, int SSQ_type, int party_id) {
    }

    @AiEventId(268566582)
    public int GetTimeAttackRewardFlag(Creature talker, int unk) {
        return 0;
    }

    @AiEventId(268566581)
    public int IsWinnerOfTimeAttackEvent(Creature talker, int unk) {
        return 0;
    }

    @AiEventId(268632231)
    public void SendUSMEvent(Creature talker, int unk0, int unk1) {
    }

    @AiEventId(268697768)
    public void BroadcastUSMEvent(Creature creature, int time, int unk2, int unk3) {
    }

    @AiEventId(268501163)
    public int IsWeakenedDeathPenalty(Creature talker) {
        return 0;
    }

    @AiEventId(268828860)
    public void AddEventCampaignPoint(Creature speller, int unk0, int unk1, int unk2, int unk3, int unk4) {
    }

    @AiEventId(268501088)
    public int GetOwnResidenceType(Creature talker) {
        return 0;
    }

    @AiEventId(268501158)
    public int GetElapsedTimeFromLastLogout(Creature talker) {
        return 0;
    }

    @AiEventId(268435616)
    public int GetOlympiadGameRule() {
        return 0;
    }

    @AiEventId(268501153)
    public int GetOlympiadRank(Creature talker) {
        return 0;
    }

    @AiEventId(268501040)
    public String IntToFStr(int intFstr) {
        return null;
    }

    @AiEventId(268501006)
    public int GetItemCollectable(Creature talker) {
        return 0;
    }

    @AiEventId(268828831)
    public void AddDynamicQuestPoint(Creature talker, int quest_id, int campaign_id, int goal_id, int point_type, int points) {
    }

    @AiEventId(268566690)
    public void AddOlympiadPoint(Creature talker, int points) {
    }

    @AiEventId(268632219)
    public void Door_RegisterStatus(Creature talker, String door_name, int inzone_id) {
    }

    @AiEventId(268501157)
    public int IsInstantAgitOwnerPledge(int pledge_id) {
        return 0;
    }

    @AiEventId(268501173)
    public int IsMemberOfWinnersPledgeLastSeason(Creature talker) {
        return 0;
    }

    @AiEventId(268501174)
    public int IsWinnerLastSeason(Creature talker) {
        return 0;
    }

    @AiEventId(268501175)
    public int IsWinnerLastSeasonAndDidNotGetPrize(Creature talker) {
        return 0;
    }

    @AiEventId(268435635)
    public String GetWinnerPledgeNameLastSeason() {
        return null;
    }

    @AiEventId(268435636)
    public String GetWinnerPlayerNameLastSeason() {
        return null;
    }

    @AiEventId(268501075)
    public int IsParticipantInDynamicContent(Creature talker) {
        return 0;
    }

    @AiEventId(268435628)
    public int GetAgathionCount() {
        return 0;
    }

    @AiEventId(268435629)
    public int GetEventGoal() {
        return 0;
    }

    @AiEventId(268566642)
    public void SunRise(Creature talker, int unk) {
    }

    @AiEventId(268435640)
    public int GetPledgeGameTopRankerPointThisSeason() {
        return 0;
    }

    @AiEventId(268435641)
    public void SetWinnerLastSeasonGotPrize() {
    }

    @AiEventId(268566697)
    public void SetSkillAllByEtcClass(Creature talker, int occupation) {
    }

    @AiEventId(268501162)
    public int GetSex(Creature talker) {
        return 0;
    }
}
