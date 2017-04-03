DROP TABLE IF EXISTS `fort_spawnlist`;
CREATE TABLE `fort_spawnlist` (
  `fortId` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `id` smallint(4) unsigned NOT NULL AUTO_INCREMENT,
  `npcId` smallint(5) unsigned NOT NULL DEFAULT '0',
  `x` mediumint(6) NOT NULL DEFAULT '0',
  `y` mediumint(6) NOT NULL DEFAULT '0',
  `z` mediumint(6) NOT NULL DEFAULT '0',
  `heading` mediumint(6) NOT NULL DEFAULT '0',
  `spawnType` tinyint(1) unsigned NOT NULL DEFAULT '0', -- 0-always spawned, 1-despawned during siege, 2-despawned 10min before siege, 3-spawned after fort taken
  `castleId` tinyint(1) unsigned NOT NULL DEFAULT '0',  -- Castle ID for Special Envoys
  PRIMARY KEY (`id`),
  KEY `id` (`fortId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `fort_spawnlist` VALUES
(101,null,35658,-53273,156650,-1896,62000,0,0),
(101,null,35659,-58672,154703,-2688,0,2,0),
(101,null,35660,-52128,157752,-2024,29864,1,0),
(101,null,35661,-52435,155188,-1768,20000,1,0),
(101,null,35662,-53944,155433,-2024,7304,1,0),
(101,null,35664,-51269,157584,-2048,39999,0,0),
(101,null,35666,-53422,158079,-2055,51999,0,0),
(101,null,35667,-50640,156000,-2056,63088,0,0),
(101,null,35667,-50938,155810,-2056,30136,0,0),
(101,null,35668,-54608,157216,-2048,62984,0,0),
(101,null,35668,-54896,157008,-2048,29696,0,0),
(101,null,35669,-53351,156814,-2048,30248,0,0),
(101,null,35669,-52160,156176,-2048,62376,0,0),
(101,null,35669,-52272,156208,-2048,29344,0,0),
(101,null,35669,-53240,156729,-2048,62712,0,0),
(101,null,36457,-53768,158042,-2048,62264,0,0),
(101,null,36393,-52680,156525,-2051,30000,3,1),
(102,null,35689,-22256,219808,-3072,32904,0,0),
(102,null,35690,-28169,216864,-3544,0,2,0),
(102,null,35691,-22992,218160,-3208,0,1,0),
(102,null,35692,-21328,218864,-2952,0,1,0),
(102,null,35694,-21520,221504,-3208,45328,1,0),
(102,null,35696,-24640,220960,-3232,60060,0,0),
(102,null,35698,-24128,221488,-3232,54428,0,0),
(102,null,35699,-20116,219724,-3232,0,0,0),
(102,null,35699,-20327,220028,-3232,33316,0,0),
(102,null,35700,-25038,219733,-3232,0,0,0),
(102,null,35700,-25246,220033,-3240,32608,0,0),
(102,null,35701,-23248,219696,-3232,0,0,0),
(102,null,35701,-23360,219904,-3232,32612,0,0),
(102,null,35701,-22176,219907,-3232,33500,0,0),
(102,null,35701,-22048,219696,-3232,0,0,0),
(102,null,36458,-23885,221728,-3232,32672,0,0),
(102,null,36394,-22368,219776,-3080,0,3,1),
(103,null,35727,16885,188473,-2760,42916,0,0),
(103,null,35728,19408,189422,-3136,0,2,0),
(103,null,35729,17984,187536,-2896,45056,1,0),
(103,null,35730,15152,188128,-2640,0,1,0),
(103,null,35731,16016,189520,-2888,0,1,0),
(103,null,35733,17008,186240,-2920,16384,0,0),
(103,null,35735,18304,188604,-2920,36864,0,0),
(103,null,35736,18048,189792,-2920,10324,0,0),
(103,null,35736,17632,189760,-2920,45348,0,0),
(103,null,35737,15282,186259,-2920,43140,0,0),
(103,null,35737,15728,186352,-2920,12288,0,0),
(103,null,35738,16784,188544,-2920,43884,0,0),
(103,null,35738,17060,188551,-2920,11484,0,0),
(103,null,35738,16080,187568,-2920,42884,0,0),
(103,null,35738,16368,187568,-2920,10432,0,0),
(103,null,36459,18397,188339,-2920,42948,0,0),
(103,null,36395,16772,188253,-2768,10232,3,2),
(104,null,35758,126080,123808,-2424,48972,0,0),
(104,null,35759,123383,121093,-2864,0,2,0),
(104,null,35760,124299,123614,-2552,49192,1,0),
(104,null,35761,124768,121856,-2296,0,1,0),
(104,null,35763,124768,124640,-2552,54480,1,0),
(104,null,35765,127920,124384,-2584,36616,0,0),
(104,null,35767,128106,122635,-2588,33036,0,0),
(104,null,35768,126208,120768,-2584,16384,0,0),
(104,null,35768,125904,120544,-2584,49024,0,0),
(104,null,35769,125920,125483,-2584,49024,0,0),
(104,null,35769,126208,125680,-2584,16384,0,0),
(104,null,35770,126186,122800,-2584,16384,0,0),
(104,null,35770,125974,122689,-2584,49024,0,0),
(104,null,35770,125974,123879,-2584,49024,0,0),
(104,null,35770,126177,124007,-2584,16384,0,0),
(104,null,36460,128133,122310,-2584,48972,0,0),
(104,null,36396,126111,123738,-2432,16248,3,3),
(105,null,35796,72544,4608,-2888,56456,0,0),
(105,null,35797,75280,1387,-3268,0,2,0),
(105,null,35798,73788,5479,-3016,55136,1,0),
(105,null,35799,72400,2896,-2760,0,1,0),
(105,null,35800,71264,4144,-3008,0,1,0),
(105,null,35802,74736,4160,-3040,34596,0,0),
(105,null,35804,73119,6121,-3047,46480,0,0),
(105,null,35805,71536,5808,-3040,56456,0,0),
(105,null,35805,71634,6146,-3040,23840,0,0),
(105,null,35806,74256,2960,-3040,23657,0,0),
(105,null,35806,74164,2606,-3040,56453,0,0),
(105,null,35807,73281,3925,-3040,23840,0,0),
(105,null,35807,73194,3703,-3040,56456,0,0),
(105,null,35807,72430,4624,-3040,56456,0,0),
(105,null,35807,72532,4853,-3040,23840,0,0),
(105,null,36461,73470,6019,-3040,56456,0,0),
(105,null,36397,72619,4569,-2889,23840,3,4),
(106,null,35827,154544,55600,-3096,58368,0,0),
(106,null,35828,159377,52403,-3312,0,2,0),
(106,null,35829,155576,56592,-3224,59224,1,0),
(106,null,35830,154704,53856,-2968,0,1,0),
(106,null,35831,153328,54848,-3216,5512,1,0),
(106,null,35833,156768,55552,-3256,32252,0,0),
(106,null,35835,154800,57146,-3257,49096,0,0),
(106,null,35836,153312,56544,-3256,58368,0,0),
(106,null,35836,153328,56896,-3256,25416,0,0),
(106,null,35837,156496,53920,-3256,58368,0,0),
(106,null,35837,156528,54272,-3256,25416,0,0),
(106,null,35838,155385,55045,-3248,25416,0,0),
(106,null,35838,155328,54800,-3256,58368,0,0),
(106,null,35838,154407,55563,-3248,58368,0,0),
(106,null,35838,154468,55805,-3256,25416,0,0),
(106,null,36462,155166,57115,-3256,58368,0,0),
(106,null,36398,154592,55527,-3098,25416,3,5),
(107,null,35858,189968,40224,-3248,0,0,0),
(107,null,35859,190423,43540,-3656,0,2,0),
(107,null,35860,188160,39920,-3376,49284,1,0),
(107,null,35861,188624,38240,-3128,0,1,0),
(107,null,35863,188626,41066,-3376,57140,1,0),
(107,null,35865,191760,40752,-3408,39112,0,0),
(107,null,35867,190992,41376,-3412,45180,0,0),
(107,null,35868,189776,41872,-3408,49148,0,0),
(107,null,35868,190048,42064,-3408,16316,0,0),
(107,null,35869,189776,36960,-3408,49148,0,0),
(107,null,35869,190048,37152,-3408,16316,0,0),
(107,null,35870,189812,39071,-3408,49148,0,0),
(107,null,35870,190044,39197,-3408,16316,0,0),
(107,null,35870,189825,40269,-3408,49148,0,0),
(107,null,35870,190048,40400,-3408,16316,0,0),
(107,null,36463,190798,41513,-3408,49148,0,0),
(107,null,36399,189984,40112,-3254,0,3,5),
(108,null,35896,118827,205186,-3176,38352,0,0),
(108,null,35897,114436,202528,-3408,0,2,0),
(108,null,35898,118880,203568,-3304,5396,1,0),
(108,null,35899,117216,205648,-3048,0,1,0),
(108,null,35900,118560,206560,-3304,48872,1,0),
(108,null,35902,120160,204256,-3336,30272,0,0),
(108,null,35904,118138,203228,-3336,17176,0,0),
(108,null,35905,120113,205939,-3336,38352,0,0),
(108,null,35905,120407,205831,-3336,5396,0,0),
(108,null,35906,116741,203878,-3336,5396,0,0),
(108,null,35906,116460,203986,-3336,38352,0,0),
(108,null,35907,117785,204712,-3336,38352,0,0),
(108,null,35907,117980,204551,-3328,5396,0,0),
(108,null,35907,118832,205280,-3328,38352,0,0),
(108,null,35907,119040,205120,-3336,5396,0,0),
(108,null,36464,117889,203183,-3336,38352,0,0),
(108,null,36400,118785,205138,-3177,5396,3,6),
(109,null,35927,158720,-70032,-2704,59020,0,0),
(109,null,35928,161876,-73407,-2984,0,2,0),
(109,null,35929,157968,-71659,-2832,59020,1,0),
(109,null,35930,159664,-72224,-2584,0,1,0),
(109,null,35932,157312,-70640,-2832,0,1,0),
(109,null,35934,159312,-68240,-2864,49028,0,0),
(109,null,35936,160832,-69056,-2866,42144,0,0),
(109,null,35937,157293,-69255,-2864,59020,0,0),
(109,null,35937,157280,-68912,-2864,27244,0,0),
(109,null,35938,161373,-71636,-2864,27244,0,0),
(109,null,35938,161371,-71992,-2864,59020,0,0),
(109,null,35939,158672,-69856,-2864,27244,0,0),
(109,null,35939,158635,-70096,-2864,59020,0,0),
(109,null,35939,159632,-70761,-2864,59020,0,0),
(109,null,35939,159670,-70518,-2864,27244,0,0),
(109,null,36465,161130,-69197,-2864,59020,0,0),
(109,null,36401,158776,-70042,-2708,27244,3,7),
(110,null,35965,70062,-60958,-2624,45292,0,0),
(110,null,35966,71436,-58182,-2904,0,2,0),
(110,null,35967,71248,-62352,-2752,12388,1,0),
(110,null,35968,71264,-60512,-2504,0,1,0),
(110,null,35970,68688,-59648,-2752,56012,1,0),
(110,null,35972,70144,-63584,-2784,18252,0,0),
(110,null,35974,70944,-63168,-2784,25448,0,0),
(110,null,35975,68995,-63605,-2784,12388,0,0),
(110,null,35975,68668,-63690,-2784,45292,0,0),
(110,null,35976,70884,-59059,-2784,12388,0,0),
(110,null,35976,70535,-59147,-2784,45292,0,0),
(110,null,35977,70194,-60871,-2784,12388,0,0),
(110,null,35977,69936,-60882,-2784,45292,0,0),
(110,null,35977,69736,-61986,-2784,12388,0,0),
(110,null,35977,69472,-61984,-2784,45292,0,0),
(110,null,36466,71273,-62968,-2784,45292,0,0),
(110,null,36433,70058,-61012,-2630,12388,3,8),
(111,null,36003,109024,-141072,-2800,62612,0,0),
(111,null,36004,105447,-139845,-3120,0,2,0),
(111,null,36005,109600,-139735,-2928,62612,1,0),
(111,null,36006,109856,-142640,-2672,0,1,0),
(111,null,36007,108223,-142209,-2920,8524,1,0),
(111,null,36009,108544,-139488,-2952,55116,0,0),
(111,null,36011,110859,-139960,-2952,40492,0,0),
(111,null,36012,111383,-141559,-2960,29804,0,0),
(111,null,36012,111494,-141882,-2952,62612,0,0),
(111,null,36013,107424,-140362,-2960,29804,0,0),
(111,null,36013,107538,-140682,-2960,62612,0,0),
(111,null,36014,110078,-141517,-2952,62612,0,0),
(111,null,36014,108896,-140928,-2952,29804,0,0),
(111,null,36014,108931,-141177,-2952,62612,0,0),
(111,null,36014,110048,-141271,-2952,29804,0,0),
(111,null,36467,111163,-140542,-2952,62612,0,0),
(111,null,36434,109080,-141070,-2801,29804,3,9),
(112,null,36034,5136,149728,-2728,0,0,0),
(112,null,36035,11513,150539,-3308,0,2,0),
(112,null,36036,7006,148242,-2856,32768,1,0),
(112,null,36037,6528,151872,-2608,0,1,0),
(112,null,36039,4384,150992,-2856,0,1,0),
(112,null,36041,5968,146864,-2888,19216,0,0),
(112,null,36043,4320,150032,-2892,0,0,0),
(112,null,36044,7345,150866,-2888,32768,0,0),
(112,null,36044,7552,150601,-2888,0,0,0),
(112,null,36045,3905,148865,-2888,32768,0,0),
(112,null,36045,4101,148594,-2888,0,0,0),
(112,null,36046,6257,149635,-2888,0,0,0),
(112,null,36046,6165,149868,-2888,32768,0,0),
(112,null,36046,5065,149635,-2888,0,0,0),
(112,null,36046,4962,149869,-2888,32768,0,0),
(112,null,36468,4326,149651,-2888,0,0,0),
(112,null,36435,5217,149697,-2736,32768,3,1),
(112,null,36436,5217,149754,-2736,32768,3,2),
(114,null,36110,60379,139950,-1592,46872,0,0),
(114,null,36111,58314,136319,-2000,0,2,0),
(114,null,36112,61864,139257,-1728,46896,1,0),
(114,null,36113,58480,139648,-1464,0,1,0),
(114,null,36114,59436,140834,-1720,47296,1,0),
(114,null,36116,60576,138064,-1752,16532,0,0),
(114,null,36118,60400,140688,-1757,48196,0,0),
(114,null,36119,61696,140832,-1752,14120,0,0),
(114,null,36119,61395,140689,-1752,46936,0,0),
(114,null,36120,58828,138054,-1752,46872,0,0),
(114,null,36120,59137,138189,-1752,14120,0,0),
(114,null,36121,60265,139991,-1752,46872,0,0),
(114,null,36121,60522,140048,-1752,14480,0,0),
(114,null,36121,60042,138817,-1752,46872,0,0),
(114,null,36121,60285,138872,-1752,14480,0,0),
(114,null,36470,60145,140737,-1752,46872,0,0),
(114,null,36439,60391,139884,-1600,14480,3,2),
(114,null,36440,60343,139892,-1600,14480,3,3),
(115,null,36141,11537,95509,-3264,49151,0,0),
(115,null,36142,9318,92253,-3536,0,2,0),
(115,null,36143,9472,94992,-3392,0,1,0),
(115,null,36144,13184,94928,-3144,0,1,0),
(115,null,36145,12829,96214,-3392,49152,1,0),
(115,null,36147,10112,93760,-3424,16384,0,0),
(115,null,36149,9485,96089,-3424,57220,0,0),
(115,null,36150,12633,93599,-3424,16384,0,0),
(115,null,36150,12365,93398,-3424,49151,0,0),
(115,null,36151,10615,96684,-3424,16384,0,0),
(115,null,36151,10344,96478,-3424,49151,0,0),
(115,null,36152,11654,95634,-3424,16384,0,0),
(115,null,36152,11422,95531,-3424,49151,0,0),
(115,null,36152,11660,94437,-3424,16384,0,0),
(115,null,36152,11420,94333,-3424,49151,0,0),
(115,null,36471,9203,95842,-3424,49151,0,0),
(115,null,36441,11549,95447,-3270,16384,3,2),
(115,null,36442,11517,95447,-3270,16384,3,4),
(116,null,36172,79686,91280,-2720,37660,0,0),
(116,null,36173,74810,90814,-3344,0,2,0),
(116,null,36174,77262,91704,-2856,5112,1,0),
(116,null,36175,79440,88752,-2600,0,1,0),
(116,null,36177,80929,90510,-2856,40192,1,0),
(116,null,36179,77600,93440,-2880,57688,0,0),
(116,null,36181,76848,92624,-2880,62456,0,0),
(116,null,36182,80031,92773,-2880,37568,0,0),
(116,null,36182,80329,92637,-2880,5012,0,0),
(116,null,36183,78231,89249,-2880,5012,0,0),
(116,null,36183,77927,89390,-2880,37920,0,0),
(116,null,36184,78812,90685,-2880,5012,0,0),
(116,null,36184,79666,91408,-2880,37660,0,0),
(116,null,36184,78611,90849,-2880,37660,0,0),
(116,null,36184,79865,91249,-2880,5012,0,0),
(116,null,36472,76880,92931,-2880,37660,0,0),
(116,null,36443,79618,91276,-2728,5012,3,4),
(116,null,36444,79641,91231,-2728,5012,3,3),
(117,null,36210,111368,-14624,-832,49151,0,0),
(117,null,36211,114221,-18762,-1768,0,2,0),
(117,null,36212,109872,-16624,-968,16384,1,0),
(117,null,36213,113481,-16058,-712,0,1,0),
(117,null,36215,112601,-13933,-960,49152,1,0),
(117,null,36217,108496,-15504,-992,0,0,0),
(117,null,36219,108880,-16492,-992,8356,0,0),
(117,null,36220,110219,-13636,-992,49151,0,0),
(117,null,36220,110478,-13435,-992,16384,0,0),
(117,null,36221,112216,-17087,-992,49151,0,0),
(117,null,36221,112482,-16883,-992,16384,0,0),
(117,null,36222,111248,-15800,-992,49151,0,0),
(117,null,36222,111487,-15701,-992,16384,0,0),
(117,null,36222,111253,-14604,-992,49151,0,0),
(117,null,36222,111486,-14503,-992,16384,0,0),
(117,null,36473,108614,-16342,-992,49151,0,0),
(117,null,36445,111323,-14680,-839,16384,3,5),
(117,null,36446,111368,-14680,-839,16384,3,4),
(117,null,36447,111412,-14681,-839,16384,3,7),
(118,null,36248,125246,95621,-1976,49151,0,0),
(118,null,36249,121072,93215,-2736,0,2,0),
(118,null,36250,122688,95760,-2112,0,1,0),
(118,null,36251,123232,94400,-1856,0,1,0),
(118,null,36253,124305,96528,-2104,49151,1,0),
(118,null,36255,126384,93728,-2144,16384,0,0),
(118,null,36257,127968,95328,-2144,32768,0,0),
(118,null,36258,124357,93571,-2144,16384,0,0),
(118,null,36258,124080,93379,-2144,49151,0,0),
(118,null,36259,126328,97008,-2144,16384,0,0),
(118,null,36259,126064,96813,-2144,49151,0,0),
(118,null,36260,125128,94482,-2144,49151,0,0),
(118,null,36260,125364,95782,-2144,16384,0,0),
(118,null,36260,125131,95677,-2136,49151,0,0),
(118,null,36260,125365,94582,-2136,16384,0,0),
(118,null,36474,128023,94941,-2144,49151,0,0),
(118,null,36448,125266,95558,-1984,16384,3,5),
(118,null,36449,125226,95559,-1984,16384,3,3),
(119,null,36286,72834,186402,-2424,54844,0,0),
(119,null,36287,71692,188004,-2616,0,2,0),
(119,null,36288,71392,184720,-2552,5528,1,0),
(119,null,36289,74288,186912,-2296,0,1,0),
(119,null,36290,71542,186410,-2552,55088,1,0),
(119,null,36292,74832,185648,-2584,24516,0,0),
(119,null,36294,70768,185632,-2584,63668,0,0),
(119,null,36295,73081,188000,-2584,22248,0,0),
(119,null,36295,72974,187690,-2584,54844,0,0),
(119,null,36296,73127,184321,-2584,22248,0,0),
(119,null,36296,73008,184000,-2584,54844,0,0),
(119,null,36297,73523,185589,-2576,22248,0,0),
(119,null,36297,73376,185380,-2584,54844,0,0),
(119,null,36297,72713,186369,-2576,54844,0,0),
(119,null,36297,72851,186593,-2584,22248,0,0),
(119,null,36475,70720,185261,-2584,54844,0,0),
(119,null,36450,72880,186364,-2425,22248,3,6),
(119,null,36451,72850,186346,-2425,22248,3,3),
(120,null,36317,100213,-55318,-488,0,0,0),
(120,null,36318,104686,-57581,-944,0,2,0),
(120,null,36319,100688,-57440,-616,16384,1,0),
(120,null,36320,100752,-53664,-360,0,1,0),
(120,null,36322,99484,-54027,-616,0,1,0),
(120,null,36324,101952,-56752,-640,32768,0,0),
(120,null,36326,99600,-57360,-648,8476,0,0),
(120,null,36327,102103,-54225,-640,32768,0,0),
(120,null,36327,102308,-54490,-640,0,0,0),
(120,null,36328,99016,-56242,-632,32768,0,0),
(120,null,36328,99229,-56507,-640,0,0,0),
(120,null,36329,101363,-55435,-640,0,0,0),
(120,null,36329,101268,-55199,-640,32768,0,0),
(120,null,36329,100168,-55434,-640,0,0,0),
(120,null,36329,100064,-55200,-640,32768,0,0),
(120,null,36476,99834,-57649,-648,0,0,0),
(120,null,36452,100280,-55302,-489,32768,3,8),
(120,null,36453,100280,-55334,-489,32768,3,7),
(121,null,36355,72365,-94294,-1264,44872,0,0),
(121,null,36356,69553,-91746,-1488,0,2,0),
(121,null,36357,70189,-93935,-1400,61576,1,0),
(121,null,36358,73680,-95456,-1144,0,1,0),
(121,null,36360,73831,-94119,-1400,45536,1,0),
(121,null,36362,70384,-95360,-1424,11308,0,0),
(121,null,36364,70704,-92960,-1424,53872,0,0),
(121,null,36365,71641,-92931,-1424,44872,0,0),
(121,null,36365,71971,-92846,-1424,12456,0,0),
(121,null,36366,72323,-96557,-1424,44872,0,0),
(121,null,36366,72653,-96469,-1424,12456,0,0),
(121,null,36367,72264,-94213,-1424,44872,0,0),
(121,null,36367,72066,-95317,-1424,12456,0,0),
(121,null,36367,71810,-95321,-1424,44872,0,0),
(121,null,36367,72526,-94209,-1424,12456,0,0),
(121,null,36477,70350,-93054,-1424,44872,0,0),
(121,null,36454,72358,-94360,-1272,12456,3,8),
(121,null,36455,72324,-94346,-1272,12456,3,9);