package com.segi.uhomecp.medicaltrans.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DateConstant {

	public static enum MonthsEnum {
		JAN("1"), FEB("2"), MAR("3"), APR("4"), MAY("5"), JUN("6"), JUL("7"), AUG("8"), SEP("9"), OCT("10"), NOV("11"), DEC("12");
		private String value;
		private MonthsEnum(String value) {
			this.setValue(value);
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	public static enum DaysEnum{
		ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,ELEVEN,TWELVE,THIRTEEN,FOURTEEN,
		FIFTEEN,SIXTEEN,SEVENTEEN,
		EIGHTEEN,NINETEEN,TWENTY,
		TWENTY_ONE,TWENTY_TWO,
		TWENTY_THREE,TWENTY_FOUR,
		TWENTY_FIVE,TWENTY_SIX,
		TWENTY_SEVEN,TWENTY_EIGHT,
		TWENTY_NINE,THIRTY,THIRTY_ONE;
	}
	
	public static final String[] bigMonth = {"1","3","5","7","8","10","12"};
	public static final String[] smallMonth = {"4","6","9","11"};
	public static final String equalMonth = "2";
	public static final String[] dayNotBelongEqualMonth = {"29","30","31"};
	public static final String[] dayNotBelongEqualMonthRun = {"30","31"};
	public static final String dayNotBelongSmallMonth = "31";
	public static final String[] smallOrEqualMonth = {"2","4","6","9","11"};
	
	public static Map<Boolean, String> isValidExpression(String[] months, String[] days) {
		Map<Boolean, String> map = new HashMap<>();
		// 排序
		Arrays.sort(days);
		Arrays.sort(months);
		List<String> smallMonthList = Arrays.asList(smallMonth);
		List<String> dayNotBelongEqualMonthList = Arrays.asList(dayNotBelongEqualMonthRun);
		List<String> daysList = Arrays.asList(days);
		List<String> monthsList = Arrays.asList(months);
		// 月为单选
		if (months.length == 1) {
			// 如果是小月，日只能选1-30
			if (smallMonthList.contains(months[0]) && daysList.contains(dayNotBelongSmallMonth)) {
				map.put(false, "小月只能选1-30日！");
				return map;
			}
			if (equalMonth.equals(months[0])) {
				// 如果是平月，日只能选1-29
				for (String day : daysList) {
					if (dayNotBelongEqualMonthList.contains(day)) {
						map.put(false, "二月只能选1-29日！");
						return map;
					}
				}
			}
		}
		ArrayList<String> smallOrEqualMonthList = new ArrayList<String>();
		smallOrEqualMonthList.addAll(Arrays.asList(smallOrEqualMonth));
		smallOrEqualMonthList.addAll(monthsList);
		Set<String> set = new HashSet<String>(smallOrEqualMonthList);
		// 所有月份都为小月或平月时，日不能包括31
		if (set.size() == smallOrEqualMonth.length && daysList.contains(dayNotBelongSmallMonth)) {
			map.put(false, "二月和小月只能选1-30日！");
			return map;
		}
		return map;
	}
}
