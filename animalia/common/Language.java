package animalia.common;


/**
 * An Enumeration of Languages containing the String Country name values and String Unicode values for the Minecraft Language Tables. Includes the Fictional Languages Klingon(Star Trek) and Quenya(The Hobbit / The Lord of the Rings)
 * 
 * @author Ghostrec35
 * @since Version 1.0.0.0
 **/
public enum Language 
{
	AFRIKAANS("South Africa", "Afrikaans", "af_ZA"),
	ARABIC("Saudi Arabia", "Arabic", "ar_SA"),
	BASQUE("Spain", "Basque", "eu_ES"),
	BULGARIAN("Bulgaria", "Bulgarian", "bg_BG"),
	CATALAN("Spain", "Catalan", "ca_ES"),
	CHINESE_SIMPLIFIED("China", "Simplified Chinese", "zh_CN"),
	CHINESE_TRADITIONAL("China", "Traditional Chinese", "zh_TW"),
	CROATION("Croatia", "Croation", "hr_HR"),
	CZECH("Czech Republic", "Czech", "cs_CZ"),
	DANISH("Denmark", "Danish", "da_DK"),
	DUTCH("Netherlands", "Dutch", "nl_NL"),
	ESTONIAN("Estonia", "Estonian", "et_EE"),
	ENGLISHAU("Australia", "Australian English", "en_AU"),
	ENGLISHCA("Canada", "Canadian English", "en_CA"),
	ENGLISHUK("United Kingdom", "British English", "en_GB"),
	ENGLISHPT("Pirate English", "Pirate English", "en_PT"),
	ENGLISHUS("United States", "American English", "en_US"),
	ESPERANTO("Uruguay", "Esperanto", "eo_UY"),
	FINNISH("Finland", "Finnish", "fi_FI"),
	FRENCHCA("Canada", "Canadian French", "fr_CA"),
	FRENCHFR("France", "French", "fr_FR"),
	GALICIAN("Spain", "Galician", "gl_ES"),
	GEORGIAN("Georgia", "Georgian", "ka_GE"),
	GERMAN("Germany", "German", "de_DE"),
	GREEK("Greece", "Greek", "el_GR"),
	HEBREW("Israel", "Hebrew", "he_IL"),
	HINDI("India", "Hindi", "hi_IN"),
	HUNGARIAN("Hungaria", "Hungarian", "hu_HU"),
	ICELANDIC("Iceland", "Icelandic", "is_IS"),
	INDONESIAN("Indonesia", "Indonesian", "id_ID"),
	IRISH("Ireland", "Irish", "ga_IE"),
	ITALIAN("Italy", "Italian", "it_IT"),
	JAPANESE("Japan", "Japanese", "ja_JP"),
	KLINGON("Qo'noS", "Klingon", "tlh_AA"),
	KOREAN("Korea", "Korean", "ko_KR"),
	KYRGYZ("Kyrgyzstan", "Kyrgyz", "ky_KG"),
	LATVIAN("Latvia", "Latvian", "lv_LV"),
	LITHUANIAN("Lithuania", "Lithuanian", "lt_LT"),
	MALAY("Malaysia", "Malay", "ms_MY"),
	MALTESE("Malta", "Maltese", "mt_MT"),
	MAORI("New Zealand", "Maori", "mi_NZ"),
	/*I know this isn't how you spell it, but I get Recompilation errors with the true Character*/
	NORWEGIAN_BOKMAL("Norway", "Norwegian Bokmal", "nb_NO"),
	NORWEGIAN_NYNORSK("Norway", "Norwegian Nynorsk", "nn_NO"),
	POLISH("Poland", "Polish", "pl_PL"),
	PORTUGUESEBR("Brazil", "Brazilian Portuguese", "pt_BR"),
	PORTUGUESEPT("Portugal", "Portuguese", "pt_PT"),
	QUENYA("Middle-Earth", "Quenya", "qya_AA"),
	ROMANIAN("Romania", "Romanian", "ro_RO"),
	RUSSIAN("Russian Federation", "Russian", "ru_RU"),
	SERBIAN("Serbia", "Serbian", "sr_SP"),
	SLOVAK("Slovakia", "Slovak", "sk_SK"),
	SLOVENIAN("Slovenia", "Slovenian", "sl_SI"),
	SPANISHAR("Argentina", "Argentinian Spanish", "es_AR"),
	SPANISHES("Spain", "Spanish", "es_ES"),
	SPANISHMX("Mexico", "Mexican Spanish", "es_MX"),
	SPANISHUY("Uruguay", "Uruguayan Spanish", "es_UY"),
	SPANISHVE("Venezuela", "Venezuelan Spanish", "es_VE"),
	SWEDISH("Sweden", "Swedish", "sv_SE"),
	THAI("Thailand", "Thai", "th_TH"),
	TURKISH("Turkey", "Turkish", "tr_TR"),
	UKRAINIAN("Ukraine", "Ukrainian", "uk_UA"),
	VIETNAMESE("Vietnam", "Vietnamese", "vi_VI"),
	WELSH("United Kingdom", "Welsh", "cy_GB");
	
	
	private String countryName;
	private String language;
	private String locale;
	
	Language(String countryName, String languageName, String localeString)
	{
		this.countryName = countryName;
		this.language = languageName;
		this.locale = localeString;	
	}
	
	/**
	 * Retrieves the code value which represents this Language/Dialect in the Minecraft Language Tables
	 * 
	 * @return String designating the code value for the Language
	 **/
	public String getLangCode()
	{
		return locale;
	}
	
	/**
	 * Retrieves the Country in which this Language is Spoken or Originated. (Dialects are listed with the Country in which the Dialect was founded, not the original Language's Country of Origin)
	 * 
	 * @return String designating the Country of Origin
	 **/
	public String getCountryOfOrigin()
	{
		return countryName;
	}
	
	public String getLanguage()
	{
		return language;
	}
}