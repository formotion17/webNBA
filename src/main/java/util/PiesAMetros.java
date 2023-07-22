package util;

public enum PiesAMetros {
    NUMERO_1(1, "0,30"),
    NUMERO_2(2, "0,60"),
    NUMERO_3(3, "0,90"),
    NUMERO_4(4, "1,20"),
    NUMERO_5(5, "1,50"),
    NUMERO_6(6, "1,80"),
    NUMERO_7(7, "2,10"),
    NUMERO_8(8, "2,40"),
    NUMERO_9(9, "2,70"),
    NUMERO_10(10, "3,00"),
    NUMERO_11(11, "3,30"),
    NUMERO_12(12, "3,60"),
    NUMERO_13(13, "3,90"),
    NUMERO_14(14, "4,20"),
    NUMERO_15(15, "4,50"),
    NUMERO_16(16, "4,80"),
    NUMERO_17(17, "5,10"),
    NUMERO_18(18, "5,40"),
    NUMERO_19(19, "5,70"),
    NUMERO_20(20, "6,00"),
    NUMERO_21(21, "6,30"),
    NUMERO_22(22, "6,60"),
    NUMERO_23(23, "6,90"),
    NUMERO_24(24, "7,20"),
    NUMERO_25(25, "7,50"),
    NUMERO_26(26, "7,80"),
    NUMERO_27(27, "8,10"),
    NUMERO_28(28, "8,40"),
    NUMERO_29(29, "8,70"),
    NUMERO_30(30, "9,00"),
    NUMERO_31(31, "9,30"),
    NUMERO_32(32, "9,60"),
    NUMERO_33(33, "9,90"),
    NUMERO_34(34, "10,20"),
    NUMERO_35(35, "10,50"),
    NUMERO_36(36, "10,80"),
    NUMERO_37(37, "11,10"),
    NUMERO_38(38, "11,40"),
    NUMERO_39(39, "11,70"),
    NUMERO_40(40, "12,00"),
    NUMERO_41(41, "12,30"),
    NUMERO_42(42, "12,60"),
    NUMERO_43(43, "12,90"),
    NUMERO_44(44, "13,20"),
    NUMERO_45(45, "13,50"),
    NUMERO_46(46, "13,80"),
    NUMERO_47(47, "14,10"),
    NUMERO_48(48, "14,40"),
    NUMERO_49(49, "14,70"),
    NUMERO_50(50, "15,00"),
    NUMERO_51(51, "15,30"),
    NUMERO_52(52, "15,60"),
    NUMERO_53(53, "15,90"),
    NUMERO_54(54, "16,20"),
    NUMERO_55(55, "16,50"),
    NUMERO_56(56, "16,80"),
    NUMERO_57(57, "17,10"),
    NUMERO_58(58, "17,40"),
    NUMERO_59(59, "17,70"),
    NUMERO_60(60, "18,00"),
    NUMERO_61(61, "18,30"),
    NUMERO_62(62, "18,60"),
    NUMERO_63(63, "18,90"),
    NUMERO_64(64, "19,20"),
    NUMERO_65(65, "19,50"),
    NUMERO_66(66, "19,80"),
    NUMERO_67(67, "20,10"),
    NUMERO_68(68, "20,40"),
    NUMERO_69(69, "20,70"),
    NUMERO_70(70, "21,00"),
    NUMERO_71(71, "21,30"),
    NUMERO_72(72, "21,60"),
    NUMERO_73(73, "21,90"),
    NUMERO_74(74, "22,20"),
    NUMERO_75(75, "22,50"),
    NUMERO_76(76, "22,80"),
    NUMERO_77(77, "23,10"),
    NUMERO_78(78, "23,40"),
    NUMERO_79(79, "23,70"),
    NUMERO_80(80, "24,00"),
    NUMERO_81(81, "24,30"),
    NUMERO_82(82, "24,60"),
    NUMERO_83(83, "24,90"),
    NUMERO_84(84, "25,20"),
    NUMERO_85(85, "25,50"),
    NUMERO_86(86, "25,80"),
    NUMERO_87(87, "26,10"),
    NUMERO_88(88, "26,40"),
    NUMERO_89(89, "26,70"),
    NUMERO_90(90, "27,00");


    private final int pie;
    private final String metros;
    

    private PiesAMetros(int pie, String metros) {
		this.pie=pie;
		this.metros=metros;
	}


	public int getPie() {
        return pie;
    }


    public String getMetro() {
        return metros;
    }
    
    public static String devolverMetros(int distancia) {
        for (PiesAMetros lista : PiesAMetros.values()) {
            if (lista.getPie()==distancia) {
                return lista.getMetro()+" Metros";
            }
        }
        return "rgb(0, 0, 0)";
    }
}

