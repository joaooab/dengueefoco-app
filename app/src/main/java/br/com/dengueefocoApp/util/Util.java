package br.com.dengueefocoApp.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public static String getData(Calendar data) {
		return formataParaString(data).split("T")[0];
	}

	public static String getData(Date data) {
		return formataParaString(data).split("T")[0];
	}

	public static String getHora(Calendar data) {
		return formataParaString(data).split("T")[1].split("-")[0];
	}

	public static String getHora(Date data) {
		return formataParaString(data).split("T")[1].split("-")[0];
	}

	public static String getNotificado(Boolean notificado) {
		return notificado ? "Sim" : "Não";
	}

	public static Date formataParaDate(String value) {
		try {
			return simpleDateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formataParaString(Date date) {
		return simpleDateFormat.format(date);
	}

	public static Calendar formataParaCalendar(String value) {
		try {
			Date date = simpleDateFormat.parse(value);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (Exception e) {
			Log.e("Util", "Formata para calendar");
			return null;
		}
	}

	public static String formataParaString(Calendar calendar) {
		return simpleDateFormat.format(calendar.getTime());
	}

}