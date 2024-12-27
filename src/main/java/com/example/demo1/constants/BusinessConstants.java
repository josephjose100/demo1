package com.example.demo1.constants;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;
import java.util.Set;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class BusinessConstants {

  // Value in seconds equivalent to 7 Days
  public static final long DEFAULT_CONTENT_CACHE_TIME = 604800L;

  public static final String DISPLAY_DATE_TIME_FORMAT = "MMM dd, yyyy hh:mm a";

  public static final String DISPLAY_DATE_FORMAT = "dd MMMM yyyy";

  public static final String DISPLAY_TIME_FORMAT = "h:mm a";

  public static final String DISPLAY_DATE_FORMAT_2 = "MMM dd, yyyy";

  public static final String FORM_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

  public static final String FORM_DATE_FORMAT = "yyyy-MM-dd";

  public static final String FORM_TIME_FORMAT = "HH:mm";

  public static final String FORM_TIME_FORMAT_EXAMPLE = "13:05";

  public static final String DEFAULT_TIMEZONE = "US/Eastern";

  public static final String ALLOWED_IMAGE_SIZE_IN_MB = "10 MB";

  public static final Long ALLOWED_IMAGE_SIZE =
      (long) (1024 * 1024 * Integer.parseInt(ALLOWED_IMAGE_SIZE_IN_MB.split(" ")[0]));

  public static final String ALLOWED_IMAGE_FORMATS = "jpg, jpeg, gif, png";

  public static final Set<String> VALID_IMAGE_FORMATS =
      stream(ALLOWED_IMAGE_FORMATS.split(", ")).collect(toSet());

  public static final int USER_FIRST_NAME_MAX_SIZE = 100;

  public static final int USER_LAST_NAME_MAX_SIZE = 100;

  public static final int EMAIL_MAX_SIZE = 100;

  public static final int PHONE_MAX_SIZE = 15;

  public static final int USER_PASSWORD_MAX_SIZE = 200;

  public static final int TIME_ZONE_MAX_SIZE = 30;

  public static final int DEFAULT_PAGE_SIZE = 10;

  public static final int PAGE_SIZE_MAX_LIMIT = 100;

  public static final String USERNAME_SPLITTER = "(::)";

  public static final String PNG_CONTENT_TYPE = "image/png";

  public static final int ADDRESS_STREET_MAX_SIZE = 200;

  public static final int ADDRESS_LINE2_MAX_SIZE = 200;

  public static final int ADDRESS_CITY_MAX_SIZE = 50;

  public static final int ADDRESS_STATE_MAX_SIZE = 2;

  public static final int ADDRESS_COUNTRY_MAX_SIZE = 50;

  public static final int ADDRESS_COUNTRY_CODE_MAX_SIZE = 3;

  public static final int ADDRESS_ZIP_CODE_MAX_SIZE = 15;

  public static final int LOCATION_NAME_MAX_SIZE = 100;

  public static final int PROPERTY_REQUEST_KEY_MAX_SIZE = 100;

  public static final String DEFAULT_CURRENCY = "USD";

  public static final int CLIENT_NAME_MAX_SIZE = 100;

  public static final int CONTACT_PERSON_MAX_SIZE = 100;

  public static final int CONTACT_PERSON_EMAIL_MAX_SIZE = 100;

  public static final int CONTACT_PERSON_PHONE_MAX_SIZE = 20;

  public static final int SPOT_NAME_MAX_SIZE = 100;

  public static final int FLEET_PLATE_NUMBER_MAX_SIZE = 30;

  public static final int FLEET_MAKE_MAX_SIZE = 100;

  public static final int FLEET_MODEL_MAX_SIZE = 100;

  public static final int FLEET_COLOR_MAX_SIZE = 30;

  public static final int FLEET_CARRIER_MAX_SIZE = 100;

  public static final int FLEET_UNIT_NUMBER_MAX_SIZE = 30;

  public static final String EXPORT_TMP_DIRECTORY_PREFIX = "sp-exp-";

  public static final int USER_DEVICE_REGISTRATION_ID_MAX_SIZE = 200;

  public static final int USER_DEVICE_NAME_MAX_SIZE = 100;

  public static final int USER_DEVICE_MODEL_MAX_SIZE = 100;

}
