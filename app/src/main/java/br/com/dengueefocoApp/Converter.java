package br.com.dengueefocoApp;

import android.util.Log;
import androidx.room.TypeConverter;
import br.com.dengueefocoApp.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Converter {

	@TypeConverter
	public Calendar fromTimestamp(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return Util.formataParaCalendar(value);
	}

	@TypeConverter
	public String dateToTimestamp(Calendar value) {
		if (value == null) {
			return null;
		}
		return Util.formataParaString(value);
	}

	@TypeConverter
	public Date fromDate(String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return Util.formataParaDate(value);
	}

	@TypeConverter
	public String toDate(Date value) {
		if (value == null) {
			return null;
		}
		return Util.formataParaString(value);
	}

}