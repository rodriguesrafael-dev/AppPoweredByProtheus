-- MySQL dump 10.13  Distrib 5.5.62, for Win32 (AMD64)
--
-- Host: localhost    Database: dbfrota
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `frota`
--

DROP TABLE IF EXISTS `frota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frota` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `numero_frota` mediumint(9) DEFAULT NULL,
  `placa` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `chassi` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `renavam` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `marca` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modelo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tipo_veiculo` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `filial` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ano` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frota`
--

LOCK TABLES `frota` WRITE;
/*!40000 ALTER TABLE `frota` DISABLE KEYS */;
INSERT INTO `frota` VALUES (1,150,'KDW 1601','9BM694004XB208841','729921050','mercedes','1218 R','truck bau',NULL,'1999'),(2,1220,'QDV 5654','9BM958164HB056265','1119717997','mercedes','2426','truck bau',NULL,'2017'),(3,1330,'OSZ 8198','9BM694000DB906311','581907493','mercedes','1319','truck bau',NULL,'2013'),(4,1660,'QDG 9104','9BM694000EB958586','1053166769','mercedes','1319','truck bau',NULL,'2014'),(5,1430,'QDV 5674','9BM958164HB604205','1119718217','mercedes','2426','truck bau',NULL,'2017'),(6,1640,'QDB 0144','9BM6940002B306449','791743519','mercedes','1319','truck bau',NULL,'2014'),(7,1320,'OTB 4908','9BM694000DB901668','585052700','mercedes','1319','truck bau',NULL,'2013'),(8,140,'JUK 2638','9BM6940002B306449','791743519','mercedes','1218 EL','truck bau',NULL,'2002'),(10,2002,'QDQ 6872','9BM958208EB973175','1045623960','mercedes','1933','Carreta',NULL,'2014'),(12,2004,'QEA 0674','9BM958444GB035010','1121255539','mercedes','2544','Carreta',NULL,'2016'),(13,2005,'QDZ 9614','9BM958444GB036310','1121238421','mercedes','2544','Carreta',NULL,'2016'),(14,2015,'QEJ 0276','95388XZZ5JE800766','1162400070','MAN','TGX28.440','Carreta',NULL,'2018'),(16,1630,'QDB 0404','9BM694000EB958622','1051489730','Mercedes Atron','1319','truck bau',NULL,'2014'),(17,1680,'QDV 5554','953658248JR802833','1119716710','volks','24.28','truck bau',NULL,'2017'),(18,1690,'QDV 5574','953658247JR802869','1119716893','volks','24.28','truck bau',NULL,'2017'),(19,130,'JVR 1883','9BM6940009B662804','207461562','mercedes','1318 EL','truck bau',NULL,'2009'),(21,340,'NSJ 7565','9BM6940009B663230','225191016','mercedes','1318 EL','truck bau',NULL,'2009'),(22,1280,'OSZ 8578','9BM694000DB904511','581918894','mercedes','1319','truck bau',NULL,'2013'),(23,1310,'OTB 5108','9BM694000DB901174','585059110','mercedes','1319','truck bau',NULL,'2013'),(24,1190,'QDV 5634','9BM958164HB057783','1119717652','mercedes','2426','truck bau',NULL,'2017'),(26,1470,'QDV 5694','9BM958164HB055834','1119718489','mercedes','2426','truck bau',NULL,'2017'),(27,360,'NSJ 7545','9BM6940009B663178','225190214','mercedes','1318 EL','truck bau',NULL,'2009'),(29,1180,'QDV 5724','9BM958164HB059537','1119718772','Mercedes Atego','2426','Truck Bau',NULL,'2017'),(30,1650,'QDB 0504','9BM694000EB958964','1051490542','mercedes','1319','truck bau',NULL,'2014'),(31,250,'JUB 7024','9BM694004YB252879','757017169','mercedes','1218 R','truck bau',NULL,'2000'),(32,420,'JVI 8978','9BM6940007B535437','938806416','mercedes','1318','truck bau',NULL,'2007'),(34,2010,'QEF 9905','953658243KR909922','1158485384','volkswagen','24280','truck bau',NULL,'2018'),(35,1340,'OTE 2088','9BM694000DB895632','585880484','mercedes','1319','truck bau',NULL,'2013'),(37,400,'OBV 4552','9BM688159CB843027','455979944','mercedes','710','bau vuc',NULL,'2011'),(38,430,'JVV 6902','9BM6881578B573225','0095611484-9','mercedes','710','bau vuc',NULL,'2007'),(39,450,'','9BM6881561B259697','757017150','mercedes','710','bau vuc',NULL,'2001'),(40,490,'NSF 5130','9BWA452R79R945737','180945548','volkswagen','Wor 8.720','bau vuc',NULL,'2009'),(42,700,'NSZ 0607','8AC903662BE040573','273241834','mercedes','Sprint','Van',NULL,'2011'),(43,800,'OFI 7059','8AC903663BE049368','390059919','mercedes','Sprinter','Van',NULL,'2011'),(44,810,'OFI 7379','8AC903663be049730','390076244','mercedes','Sprinter','Van',NULL,'2011'),(45,820,'OFM 3161','8AC903663CE056702','452035996','mercedes','Street','furgao',NULL,'2011'),(48,1260,'OTA 1406','8AC906635DE077548','558687580','mercedes','Sprinter','Van',NULL,'2013'),(49,1610,'QDK 1063','9BM979028ES028054','1050710263','mercedes','accelo 815','bau vuc',NULL,'2014'),(50,1620,'QDK 0973','9BM979028ES026170','1050706533','mercedes','accelo 815','bau vuc',NULL,'2014'),(51,2017,'QDY 1797','9535H5TB1KR913238','1165929586','volkswagen','9.170 DRC','bau vuc',NULL,'2019'),(52,1590,'QDK 3080','8AC906635EE097076','1031325015','mercedes','Sprint','Van',NULL,'2014'),(55,60,'QEW 6433','9C2KD1000JR116468','1148172731','Honda','NXR 160','Moto',NULL,'2018'),(56,1500,'OFL 2669','9C2KD0560CR502022','393172864','Honda','NXL 150','Moto',NULL,'2012'),(58,220,'NSM 2702','9BM6940009B663533','201534746','mercedes','1318 EL','truck bau',NULL,'2009'),(59,230,'AAY 0372','9BM6940001B282970','768236126','mercedes','1218 EL','truck termico',NULL,'2001'),(61,520,'AAW 9676','9BM6940001B280390','768134706','mercedes','1218','toco bau',NULL,'2001'),(62,840,'JVH 5725','9BM6940008B612469','979348978','mercedes','1318','toco termico',NULL,'2008'),(63,1110,'OTC 6009','9C2JC4110CR496725','497730405','Honda','CG 125','Moto',NULL,'2012'),(64,780,'JVO 5721','9BM6940008B558391','949768723','mercedes','1318','Container',NULL,'2007/08'),(65,290,'OBW 7651','9533172S2BR149084','423631373','volks','1318','Munck',NULL,'2011'),(67,570,'QDV 5614','9531M52P3JR802888','0111971746-6','volks','8160','bau vuc',NULL,'2017'),(68,1160,'QEC 6283','9531M52P8HR709150','0111846471-8','Volks','8.160','Bau vuc',NULL,'2017'),(70,380,'OTZ 6953','9BM693186DB926504','1001454623','mercedes','1719','Usina',NULL,'2013'),(71,600,'QDR 6187','9BM958154HB055832','1130116473','mercedes','1719','Usina',NULL,'2017'),(72,730,'NSV 2085','9BM693186BB784804','331299364','mercedes','1718','Usina',NULL,'2011'),(73,2019,'QDY 1777','9535H5TB9KR916081','1165928750','volkswagen','9.170 DCR 4x2','Carroceria',NULL,'2018/2019'),(74,2018,'QEA 4517','9535H5TB0KR917944','1166735696','volkswagen','9.170 DCR 4x2','Carroceria',NULL,'2018/2019'),(75,280,'CGS 8481','9BM688156WB152988','693785020','mercedes','710','Carroceria',NULL,'1998'),(77,1440,'OTF 5578','9C2JC4110ER702080','586304339','Honda','CG 125','Moto',NULL,'2013'),(78,900,'JTV 0804','9BRBJ0120X1018269','0071585893-9','Toyota','Toyota','Bandeirante',NULL,'1999'),(79,560,'QEA 2920','9C2KC167FR520260','0107201477-4','Honda','CG 150','Moto',NULL,'2015'),(80,720,'JVX 4248','8AJDR22G284006981','945779887','Toyota','Hilux','Camionete',NULL,'2007/2008'),(81,760,'QDI 0485','9C2KC1680FR542150','1056678744','Honda','CG 150','Moto',NULL,'2015'),(83,1360,'OTA 4478','94BF1543DDR021363','583377165','Faccini','bau','Semi-Rebo',NULL,'2013'),(84,1370,'OTA 4948','94BF1543DDR021362','583382100','Faccini','bau','Semi-Rebo',NULL,'2013'),(85,1380,'OTA 5088','94BF1543DDR021361','583383769','Faccini','bau','Semi-Rebo',NULL,'2013'),(86,1400,'OBU 6549','9A9708200B0DX9458','379046350','R/MJC','bau','Semi-Rebo',NULL,'2011'),(88,1420,'OBU 6839','9A9708200B0DX9448','379095700','R/MJC','bau','Semi-Rebo',NULL,'2011'),(90,1460,'OFW 7874','9A9708200D0DX9315','544758463','R/MJC','bau','Semi-Rebo',NULL,'2013'),(91,3010,'QDZ 9784','94BF1473HHR028378','1121235040','Faccini','bau Frigorifico','Semi-Rebo',NULL,'2017'),(92,3020,'QDZ 9824','94BF1473HHR028379','1121236399','Faccini','bau','Semi-Rebo',NULL,'2017'),(93,3030,'QEA 0974','94BF1473HHR028380','1121298637','Faccini','bau','Semi-Rebo',NULL,'2017'),(94,3040,'QEK 1906','94BF1473JJR031443','1162852477','Faccini','bau Frigorifico','Semi-Rebo',NULL,'2018'),(95,860,'JVA 1018','9BWCA05W47P036210','898513847','volksvagem','Gol','veiculo leve',NULL,'2006'),(96,870,'JUX 3465','9BWAA05W59P034475','977783260','volksvagem','Gol','veiculo leve',NULL,'2008'),(98,1050,'JVL 9864','9BWAA05WX9T010194','976136210','volksvagem','Gol','veiculo leve',NULL,'2009'),(99,1060,'OFN 5566','9BWAA05W5DP041614','479428581','volksvagem','Gol','veiculo leve',NULL,'2013'),(101,850,'NSF-0689','9BWAA05W5AP058215','172848431','volksvagem','Gol','veiculo leve',NULL,'2009/10'),(102,2013,'QEH 1634','9BWAG45U2JT114624','115099458','volksvagem','NV Gol','veiculo leve',NULL,'2018'),(103,2014,'QEF 0875','9BWAG45U7JT158764','','volksvagem','NV Gol','veiculo leve',NULL,'2018'),(104,990,'OSX 7049','9BD27804MD7578870','496172917','Fiat','Strada','veiculo leve',NULL,'2013'),(107,190,'CGS 7703','','','GMC','12170','truck bau',NULL,'1998'),(108,200,'CGS 8472','','','GMC','12170','Toco bau',NULL,'1998'),(109,330,'5432-07-01','','','Chevrolet','S-10','Camionete',NULL,'2004'),(110,470,'JUG 2321','','','Renault','Master','Furgão',NULL,'2002'),(112,610,'JWA 2374','','','Iveco','Daily','Van',NULL,'2002'),(113,480,'JUX 2459','','','Fiat','2.8','furgao',NULL,'2004'),(114,910,'JVM 7789','','','volkswagen','Kombi','',NULL,'2005/06'),(116,210,'CGS 1786','','','mercedes','1214','toco termico',NULL,'1997/98'),(117,100,'CGR 4823','','','mercedes','1418','truck bau',NULL,'1998'),(119,320,'CGR 5758','','','mercedes','1418','Munck',NULL,'1418'),(120,260,'CDM 7653','','','mercedes','1218 R','truck bau',NULL,'1998/99'),(121,120,'CDL 7125','','','mercedes','1218 EL','truck bau',NULL,'1998'),(122,170,'CGR 4822','','','mercedes','1418','truck bau',NULL,'1998'),(123,440,'CDL 5897','','','mercedes','710','bau vuc',NULL,'1998'),(124,1030,'JUZ 1706','','','volkswagen','Gol','2008',NULL,''),(125,1090,'NSH 6786','','','volksvagem','Voyage','2009',NULL,''),(126,1080,'NSR 2449','','','volksvagem','Gol','2010',NULL,''),(129,530,'CDM 7646','','','mercedes','712 C','bau vuc',NULL,'1998'),(130,1670,'QDW 5272','8AC906633FE101555','1047438418','mercedes','Sprint','Van',NULL,'2014/2015'),(131,1020,'NSO 2297','9C2KD0530AR003797','232910472','Honda','NXL 150','Moto',NULL,'2010'),(132,920,'NSN 1791','9BWMF07X9AP019647','196340977','volkswagen','kombi','',NULL,'2010'),(133,2016,'QEM 6506','93ZC0359ZK8482818','1163752751','Iveco','Daly 30S13','Van',NULL,'2018/2019'),(134,580,'QEI 4791','','1079213292','Motocar','MCF 200','Moto',NULL,'2015'),(136,670,'QDS-2964','9C2KD1000GR023004','0108980475-7','Honda','NXR 160','Moto',NULL,'2016'),(137,1240,'OTA 1466','8AC906635DE077549','558689221','mercedes','Sprint','Van',NULL,'2013'),(139,930,'JUV 3076','9BWGB07X56P000237','861714466','volkswagen','Kombi','',NULL,'2005'),(140,940,'NSN 1431','9BWMF07XXAP019625','196335256','volkswagen','Kombi','',NULL,'2009'),(141,1560,'OFL 2679','9C2KD0560CR501124','393174298','Honda','NXL 150','Moto',NULL,'2011'),(142,950,'OBZ 0198','9BWMF07X2CP009612','369888626','volkswagen','Kombi','',NULL,'2011'),(143,390,'OBW 1151','9C2KD0560CR504765','421120185','Honda','NXL 150','Moto',NULL,'2011'),(144,660,'JVE 6946','8AC9036628A995605','983228264','mercedes','Sprint','Van',NULL,'2008'),(147,500,'OBT 5152','9BM688159CB839001','455386960','mercedes','710','bau vuc',NULL,'2011'),(148,750,'JVV 1958','8AC9036628A971785','944757693','mercedes','Sprint','Van',NULL,'2008'),(149,770,'JVS 7741','8AC9036627A969378','951673408','mercedes','Sprint','Van',NULL,'2007'),(151,980,'OTC 4617','9BM979026DS014605','568808150','mercedes','815','bau vuc',NULL,'2013'),(152,2020,'QVC 5393','93ZK35B01K8483938','1190949331','Iveco','Daily','Van',NULL,'2018/2019'),(153,2011,'QEI 7436','9535H5TBXKR917868','1162345630','volks','9.170 DCR 4x2','C. Aberta',NULL,'2018/2019'),(155,540,'NSH 3465','8AC903662AE032865','224301160','mercedes','Street','Furgão',NULL,'2010'),(157,2007,'QER 4563','9531M52P1JR823142','1146054332','volks','8160','C. Aberta',NULL,'2018'),(158,70,'QEI 6275','9C2KC2500JR018385','1159499729','Horda','CG160 Start','Moto/ bau',NULL,'2018'),(159,1600,'QDK 1133','9BM979026ES029764','1050711855','mercedes','accelo 815','bau vuc',NULL,'2014'),(161,2008,'QES 7804','9535H5TB5KR903750','1155301479','VWL','9.17','C. Aberta',NULL,'2018/2019'),(162,1010,'JVQ 5190','9BD15828814187297','749638290','Fiat','Uno','Passeio',NULL,'2001'),(163,1200,'JUY 1315','9C2JA0410R047657','925247944','Honda','Biz','Moto',NULL,'2007'),(164,270,'CGS 4650','9BM688156VB126069','690742223','mercedes','710','bau vuc',NULL,'1998'),(165,460,'OBT 5192','9BM688159CB843134','455388830','mercedes','710','bau vuc',NULL,'2011'),(166,30,'QEX 5263','9C2KD1000JR116454','1148481556','Honda','NXR 160','Moto',NULL,'2018'),(167,50,'QEX 6313','9C2KD1000JR113954','1148502472','Honda','NXR 160','Moto',NULL,'2018'),(168,620,'QDF 4814','93XXNKB8TGCF12632','1052743088','Mitisubishi','L200','',NULL,'2015'),(169,310,'QDS 8879','93XPRK94W8C810597','963766503','Mitisubishi','L200','Camionete',NULL,''),(171,1490,'OBW 0961','9C2KD0560CR504441','421112492','Honda','NXL 150','Moto',NULL,'2012'),(172,1510,'OTC 5939','9C2JC4110CR500783','497725975','Honda','CG 125','Moto',NULL,'2012'),(173,1390,'OSX 2683','9C2JC4110DR424004','538034319','Honda','CG 125','Moto',NULL,'2012'),(174,590,'QDZ 9662','9C2KC1670FR012448','0108031043-3','Honda','CG 150','Moto',NULL,'2016'),(175,1350,'OBZ 2805','9C2KC1670BR620111','342729845','Honda','CG 150','Moto',NULL,'2011'),(176,1540,'NSO 2267','9C2KD0530AR005858','232909512','Honda','NXL 150','Moto',NULL,'2010'),(177,1520,'OSX 1024','9C2JC4110DR722852','545785103','Honda','CG 125','Moto',NULL,'2013'),(179,1210,'QDR 1901','9BWJB45U1FP158550','1039787298','volkswagen','Saveiro','Camionete',NULL,'2015'),(180,1570,'OTF 1606','9C2JC4110DR124170','565155873','Honda','CG 125','Moto',NULL,'2013'),(182,890,'JTK 5283','9BWZZZ113TP001216','0064942628-2','volks','Fusca','Passeio',NULL,'1996'),(183,690,'OFM 4082','9C2JC4110CR496885','0045981675-6','Honda','CG 125','Moto',NULL,'2012'),(184,680,'JTY 8097','93ZC3570118302573','0076225320-7','Iveco','Daily','Van',NULL,'2001'),(185,2012,'OSC 1208','93XHYKB8TFCE91769','1014601174','Triton','L200','Camionete',NULL,'2015'),(190,300,'JVT 3983','9BM6940009B679456','207579091','Mercedes','1318 EL','Truck Bau',NULL,'2009'),(192,2009,'QEO 6064','9536E7234KR900163','1153763459','Volks','13.190','toco bau',NULL,'2018'),(195,650,'JWE 6008','8AC9036628A971759','948193069','Mercedes','Sprint','van',NULL,'2008'),(196,2001,'OSX 1607','9BM958207DB910225','566929040','Mercedes','1933','Carreta',NULL,'2013'),(197,2003,'QDZ 9864','9BM958444GB035632','1121237360','Mercedes','2544','carreta',NULL,'2016'),(198,1290,'OTB 4688','9BM694000DB897442','585042071','Mercedes','1319','Truck Bau',NULL,'2013'),(200,180,'JUB 5612','9BM6940002B292424','776896300','Mercedes','1218 EL','Truck Bau',NULL,'2002'),(201,1170,'QDV 5604','9BM958164HB053849','1119717270','Mercedes','2426','Truck bau',NULL,'2017'),(202,410,'JUB 7034','9BM694004YB253454','757017207','Mercedes','1218','Truck Ba',NULL,'2000'),(203,830,'OFM 3121','8AC903663CE055820','452034469','Mercedes','Street','Furgao',NULL,'2011'),(204,1250,'OTA 1206','8AC906635DE077550','558683886','Mercedes ','Street','Furgao',NULL,'2013'),(205,790,'OFI 7449','8AC903663BE049729','390081167','Mercedes','Sprinter','Van',NULL,'2011'),(206,350,'NSJ 7555','9BM6940009B662827','225190680','Mercedes','1318 EL','Truck Bau',NULL,'2009'),(207,710,'NSW 0302','9BM693186BB758183','284413399','Mercedes','1718','Usina',NULL,'2011'),(211,110,'NSH 5272','9BM6940009B681979','199281874','Mercedes','1318 EL','Truck Bau',NULL,'2009'),(213,630,'juq 1962','93w231f1141015417','825733766','fiat','ducato','van',NULL,'2004'),(216,2000,'A997Y02724N','','','YALE','GP050LX GLP','EMPILHADEIRA',NULL,'2015'),(217,1270,'otd 8481','9bm695304db916313','569000980','mercedes','2324','basculante',NULL,'2013'),(218,1300,'nsi 7463','8ajfz29g3a6105091','209859032','Toyota','Hilux','Caminhonete',NULL,'2010'),(219,1410,'nsq 8006','9a9710900apdz6171','229749658','r/jnfig','bau','semi rebo',NULL,'2010'),(220,1450,'nss 5547','9a9710900apdz6173','234844752','r/jnfig','bau','semi rebo',NULL,'2010'),(221,1040,'jvh 0687','9bwaa05w49t115166','987489313','volkswagem','gol','veiculo leve',NULL,'2009'),(222,1070,'ofn 5626','9bwaa05w7dp037872','479431485','volkswagem','gol','veiculo leve',NULL,'2013'),(223,20,'qep 3969','97umcf200gm001104','1135657456','motocar','mcf/200','moto bau',NULL,'2015'),(224,740,'jvn 6562','93zk53b01a8414222','197373321','iveco','daily','van',NULL,'2009'),(225,960,'obz 0098','9bwmf07x9ap019647','196340977','volkswagem','kombi','van',NULL,'2010'),(226,550,'nsg 6175','8ac903662ae032864','224047590','mercedes','street','furgao',NULL,'2010'),(227,970,'obz 0308','9bwmf07x1cp009651','369895037','volkswagem','kombi','van',NULL,'2012'),(228,1530,'otf 5428','9c2kd0550dr217096','586301992','honda','nxl 150','moto',NULL,'2014'),(229,2006,'qer 4653','9531m52p5jr823237','1146058257','volkswagem','8160','bau vuc',NULL,'2018'),(230,10,'qep 3119','97umcf200gm001105','1135644028','motocar','mfc/200','moto bau',NULL,'2015'),(231,1480,'qda 6212','9bg148ek0fc420643','1040681210','chevrolet','s10','camionete',NULL,'2015'),(232,1120,'jum 6564','9c2kc08504r015642','856033120','honda','cg 150','moto',NULL,'2004'),(233,1580,'otf 5246','9c2jc4110dr810225','565256920','honda','cg 125','moto',NULL,'2013'),(234,1230,'osc 1208','93xhykb8tfce91769','1014601174','triton','l200','camionete',NULL,'2015'),(235,1550,'nsu 4850','9bg138kj0bc428532','267776322','chevrolet','s10','camionete',NULL,'2010/2011'),(237,1700,'hhe0010419','ngah 00882','','case','580 l','retro escavadeira',NULL,'1994'),(238,1710,'nam416389','motor 43814','','case','430 rops','mini carregadeira',NULL,'1994'),(239,1730,'nbsaf1691','motor 129543','','case','cx160b','escavadeira',NULL,'1994'),(240,1720,'hbznw20evcae01660','motor 36369454','','case','tr w20e','pa carregadeira',NULL,'1994'),(241,1740,'762362','907419/0004','','manitou','mtx 1740 slt s3e3','manipuladora',NULL,'1994'),(242,1750,'jafsv185edm466566 vi','1906sgss','','case','sv185','mini carregadeira',NULL,'2013'),(243,1760,'hbzn580nleah12272','1907mag','','case','580n 4x2 rops','retro escavadeira',NULL,'2014'),(244,1770,'10000104e0b004160','1908mag','','dynapac','ca 150 t3','rolo compactador',NULL,'2014'),(245,1780,'nem481002','1909sgss','','case','sv185','mini carregadeira',NULL,'2014'),(246,1800,'c849t04489f','','','yale','mr 16h','empilhadeira',NULL,'2008'),(247,1810,'c849t04489f','','','yale','mr 16h','empilhadeira',NULL,'2008'),(248,1820,'c849t04411f','','','yale','mr 16h','empilhadeira',NULL,'2008'),(249,1830,'c849t04644f','','','yale','mr 16h','empilhadeira',NULL,'2008'),(250,1840,'c849t03095d','','','yale','mr 16n','empilhadeira',NULL,'2005'),(251,1850,'c849t05067f','','','yale','mr 16h','empilhadeira',NULL,'2008'),(252,1860,'c849t05199f','','','yale','mr 16h','empilhadeira',NULL,'2008'),(253,1870,'c849t05655h','','','yale','mr 16h','empilhadeira',NULL,'2010'),(254,1880,'c849t05716h','','','yale','mr 16h','empilhadeira',NULL,'2010'),(255,1890,'341834b00077','','','still','m fmx 17 e','empilhadeira',NULL,'2011'),(256,1100,'341834d02766','','','still','fmx 17','empilhadeira',NULL,'2013'),(257,1130,'b852x02020z','','','yale','ms 16-46','empilhadeira',NULL,'2002'),(258,1140,'a997y02846p','','','yale','gp050lx gpl','empilhadeira',NULL,'2016'),(259,1150,'a997y02845p','','','yale','gp050lx gpl','empilhadeira',NULL,'2016'),(260,1900,'b896n03112f','','','yale','mpe 060f','transpaleteira',NULL,''),(261,1910,'b896n03799f','','','yale','mpe 060f','transpaleteira',NULL,''),(262,1920,'b896n03798f','','','yale','mpe 060f','transpaleteira',NULL,''),(263,1930,'b896n03114f','','','yale','mpe 060f','transpaleteira',NULL,''),(264,1940,'b896n03109f','','','yale','mpe 060f','transpaleteira',NULL,''),(265,1950,'b896n03102f','','','yale','mpe 060f','transpaleteira',NULL,''),(266,1960,'b896n03117f','','','yale','mpe 060f','transpaleteira',NULL,''),(267,1970,'b896n03118f','','','yale','mpe 060f','transpaleteira',NULL,''),(268,1980,'b896n03116f','','','yale','mpe 060f','transpaleteira',NULL,''),(269,1990,'b896n03113f','','','yale','mpe 060f','transpaleteira',NULL,'');
/*!40000 ALTER TABLE `frota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_os`
--

DROP TABLE IF EXISTS `material_os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_os` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) DEFAULT NULL,
  `quantidade` int(10) unsigned DEFAULT NULL,
  `stattus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_os`
--

LOCK TABLES `material_os` WRITE;
/*!40000 ALTER TABLE `material_os` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_os` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `os`
--

DROP TABLE IF EXISTS `os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `os` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `frota_id` bigint(20) unsigned NOT NULL,
  `material_os_id` bigint(20) NOT NULL,
  `servico_os_id` bigint(20) NOT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `dtEntrada` date DEFAULT NULL,
  `dtSaida` date DEFAULT NULL,
  `horaEntrada` varchar(10) DEFAULT NULL,
  `horaSaida` varchar(10) DEFAULT NULL,
  `stattus_os` varchar(45) DEFAULT NULL,
  `stattus_servico_os` varchar(45) DEFAULT NULL,
  `stattus_material_os` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `os_FKIndex1` (`servico_os_id`),
  KEY `os_FKIndex2` (`material_os_id`),
  KEY `os_FKIndex3` (`frota_id`),
  CONSTRAINT `os_ibfk_1` FOREIGN KEY (`servico_os_id`) REFERENCES `servico_os` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `os_ibfk_2` FOREIGN KEY (`material_os_id`) REFERENCES `material_os` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `os_ibfk_3` FOREIGN KEY (`frota_id`) REFERENCES `frota` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `os`
--

LOCK TABLES `os` WRITE;
/*!40000 ALTER TABLE `os` DISABLE KEYS */;
/*!40000 ALTER TABLE `os` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico_os`
--

DROP TABLE IF EXISTS `servico_os`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico_os` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stattus` varchar(20) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico_os`
--

LOCK TABLES `servico_os` WRITE;
/*!40000 ALTER TABLE `servico_os` DISABLE KEYS */;
/*!40000 ALTER TABLE `servico_os` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-10 10:18:00
