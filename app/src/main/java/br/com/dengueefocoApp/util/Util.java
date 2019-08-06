package br.com.dengueefocoApp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public static String getDataHojeString() {
		return simpleDateFormat.format(Calendar.getInstance());
	}

}
