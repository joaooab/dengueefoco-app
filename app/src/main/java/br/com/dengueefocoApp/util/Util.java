package br.com.dengueefocoApp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public static String getDataHojeString() {
		return simpleDateFormat.format(Calendar.getInstance().getTimeInMillis());
	}

	public static String getData(String dataVisita) {
		return dataVisita.split("T")[0];
	}

	public static String getHora(String dataVisita) {
		return dataVisita.split("T")[1].split("-")[0];
	}

	public static String getNotificado(Boolean notificado) {
		return notificado ? "Sim" : "Não";
	}

}
