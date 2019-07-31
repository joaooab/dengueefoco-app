package br.com.maximasistemas.dengueefoco_app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static String getDataHojeString() {
		return simpleDateFormat.format(new Date());
	}

}
