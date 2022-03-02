package kr.co.atg.apds.komipo_main.common.constant;

public class Constants {
  /*
   * Menu Type String
   */
  public static final String UPPER_MENU = "collapse";
  public static final String LAST_MENU = "item";

  /*
   * Equipment Master String
   */
  public static final String EQUIPMENT_FAN = "fan";
  public static final String EQUIPMENT_MOTOR = "motor";
  public static final String EQUIPMENT_PUMP = "pump";
  public static final String EQUIPMENT_TURBINE = "turbine";
  public static final String EQUIPMENT_ALL = "all";

  /*
   * Equipment Information Table Name
   */
  public static final String EQUIPMENT_FAN_TABLE_NAME = "Vib_Master_Fan";
  public static final String EQUIPMENT_MOTOR_TABLE_NAME = "Vib_Master_Motor";
  public static final String EQUIPMENT_PUMP_TABLE_NAME = "Vib_Master_Pump";
  public static final String EQUIPMENT_TURBINE_TABLE_NAME = "Vib_Master_Turbine";

  /*
   * Authentication Information Table Name
   */
  public static final String AUTH_USER_TABLE_NAME = "t_user";

  /*
   * ChartJS label String
   */
  public static final String CHARTJS_LABEL = "XY Values";

  /*
   * Not Normal Input String
   */
  public static final String INEFFICIENT_KEYS = "Inefficient Keys";
  public static final String OBJECT_NOT_FOUND = "Object Not Found";
  public static final String INVALID_PARAMS = "Invalid Key Parameter";
  public static final String INVALID_MULTIPART_FILE = "Invalid Multipart File";

  /*
   * Query Failed Message
   */
  public static final String INSERTION_FAILED = "Insertion is Failed";
  public static final String DUPLICATED_KEY_FAILED = "Unique Column Violation";
  public static final String UPDATE_FAILED = "Update is Failed";
  public static final String QUERY_FAILED = "Query Failed";

  /*
   * Error Messages
   */
  public static final String URL_NOT_FOUND = "URL Not Found";
  public static final String ACCESS_FORBIDDEN = "Do not have Access Right";

  /*
   * Sensor Orientation (In DataBase)
   */
  public static final int SENSOR_TYPE_UNKNOWN = 0;
  public static final int SENSOR_TYPE_HORIZONTAL = 1;
  public static final int SENSOR_TYPE_VERTICAL = 2;
  public static final int SENSOR_TYPE_AXIAL = 3;
  public static final int SENSOR_TYPE_RADIAL = 4;
  public static final int SENSOR_TYPE_RADIAL_X = 5;
  public static final int SENSOR_TYPE_RADIAL_Y = 6;
  public static final int SENSOR_TYPE_TANGENTIAL = 7;

  public static final String SENSOR_TYPE_UNKNOWN_STRING = "unknown";
  public static final String SENSOR_TYPE_HORIZONTAL_STRING = "horizontal";
  public static final String SENSOR_TYPE_VERTICAL_STRING = "vertical";
  public static final String SENSOR_TYPE_AXIAL_STRING = "axial";
  public static final String SENSOR_TYPE_RADIAL_STRING = "radial";
  public static final String SENSOR_TYPE_RADIAL_X_STRING = "radial_x";
  public static final String SENSOR_TYPE_RADIAL_Y_STRING = "radial_y";
  public static final String SENSOR_TYPE_TANGENTIAL_STRING = "tangential";

  /*
   * Excel Template File Path
   */
  public static final String EQUIPMENT_INFORMATION_TEMPLATE_FILE_PATH = "templateFiles/EquipmentInformationTemplate.xlsx";
  public static final String DT_AND_RULE_TEMPLATE_FILE_PATH = "templateFiles/DtAndRuleTemplate_.xlsx";

  /*
   * Equipment Information Template Excel Row Cell Index
   */
  public static final int DT_RULE_ROW_START = 3;
  public static final int DT_RULE_COL_START = 1;

  /*
   * Equipment Information Template Excel Row Cell Index
   */
  public static final int ROW_TEMPLATE_START_INDEX = 0;
  public static final int TABLE_COLUMN_NAME_ROW_INDEX = 1;
  public static final int ROW_START_INDEX = 2;

  /*
   * Related with Authority Management
   */
  public static final String AUTH_ROLE_STRING = "ROLE_ADMIN";
  public static final String AUTH_URL_START_STRING = "/api/v1/admin/authority";
  public static final String AUTH_USER_GET_FUNCTION_NAME = "system.user.get";
}
