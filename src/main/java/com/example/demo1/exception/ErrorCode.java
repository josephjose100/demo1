package com.example.demo1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {

	NO_USER_AVAILABLE("error.unavailable", "No users are available at present.Please add user availabilities", NOT_FOUND),
	
	SHIFT_ERROR("error.wrong.shift","Starting time should be before ending time",BAD_REQUEST),
	
	BREAK_TIME_ERROR("error.wrong.break.time","Break time should be in between shift time",BAD_REQUEST),
	
	SERVER_ERROR("error.internal.server", "Internal server error occured. Server Message : {0}",
		INTERNAL_SERVER_ERROR),

	CONFIG_UPDATE_ERROR("error.config.update", "Error occured while updating config", BAD_REQUEST),

	CONFIG_FETCH_ERROR("error.config.fetch", "Error occured while fetching config", BAD_REQUEST),

	BOL_ERROR("error.bol", "Error occured while uploading BOL", BAD_REQUEST),

	ACCESS_DENIED("error.access.denied", "You are not authorized to access this API.", FORBIDDEN),

	CLIENT_ACCESS_DENIED("error.client.access.denied",
		"You are not authorized to access this Client Resources.", FORBIDDEN),

	USER_NOT_AUTHENTICATED("error.user.not.authenticated",
		"You have not logged in to Application, please login and try again.", UNAUTHORIZED),

	INVALID_PAGE("error.invalid.page",
		"Provided Page is Invalid : {0}. Valid page should be greater than 0.", BAD_REQUEST),

	INVALID_PAGE_SIZE("error.invalid.page.size",
		"Provided Page Size is Invalid : {0}. Valid Page Size is in range {1} to {2}", BAD_REQUEST),


	FIRST_NAME_CREATE_PROFILE_REQUEST_EMPTY("NotBlank.createCustomerProfileRequest.firstName",
		"First Name is required.", BAD_REQUEST),

	FIRST_NAME_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.firstName",
		"First Name can be of maximum {0} characters long.", BAD_REQUEST),

	LAST_NAME_CREATE_PROFILE_REQUEST_EMPTY("NotBlank.createCustomerProfileRequest.lastName",
		"Last Name is required.", BAD_REQUEST),

	LAST_NAME_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.lastName",
		"Last Name can be of maximum {0} characters long.", BAD_REQUEST),

	EMAIL_CREATE_PROFILE_REQUEST_EMPTY("NotBlank.createCustomerProfileRequest.email",
		"Email or phone is required.", BAD_REQUEST),

	EMAIL_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.email",
		"Email can be of maximum {0} characters long.", BAD_REQUEST),

	EMAIL_CREATE_PROFILE_REQUEST_INVALID("Email.createCustomerProfileRequest.email",
		"Provided email address is invalid.", BAD_REQUEST),

	PHONE_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.phone",
		"Phone can be of maximum {0} characters long.", BAD_REQUEST),

	PHONE_CREATE_PROFILE_REQUEST_INVALID("NotValid.createCustomerProfileRequest.phone",
		"Provided phone number is invalid.", BAD_REQUEST),

	TIME_ZONE_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.timeZone",
		"Time Zone can be of maximum {0} characters long.", BAD_REQUEST),

	TIME_ZONE_CREATE_PROFILE_REQUEST_INVALID("NotValid.createCustomerProfileRequest.timeZone",
		"Provided Time Zone is invalid.", BAD_REQUEST),

	PASSWORD_CREATE_PROFILE_REQUEST_EMPTY("NotBlank.createCustomerProfileRequest.password",
		"Password is required.", BAD_REQUEST),

	PASSWORD_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.password",
		"Password can be of maximum {0} characters long.", BAD_REQUEST),

	CONFIRM_PASSWORD_CREATE_PROFILE_REQUEST_EMPTY(
		"NotBlank.createCustomerProfileRequest.confirmPassword", "Confirm Password is required.",
		BAD_REQUEST),

	CONFIRM_PASSWORD_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.confirmPassword",
		"Confirm Password can be of maximum {0} characters long.", BAD_REQUEST),

	PASSWORD_CONFIRM_PASSWORD_CREATE_PROFILE_REQUEST_NOT_MATCH(
		"NotMatch.createCustomerProfileRequest.confirmPassword",
		"Password and Confirm Password are not matching.", BAD_REQUEST),

	DOB_CREATE_PROFILE_REQUEST_INVALID("NotValid.createCustomerProfileRequest.dob",
		"Provided DOB is invalid.", BAD_REQUEST),

	DOB_CREATE_PROFILE_REQUEST_FUTURE("Future.createCustomerProfileRequest.dob",
		"DOB can't be of future Date.", BAD_REQUEST),

	ADDRESS_CREATE_PROFILE_REQUEST_NULL("NotNull.createCustomerProfileRequest.address",
		"Address is required.", BAD_REQUEST),

	GENDER_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.gender",
		"Gender can be of maximum {0} characters long.", BAD_REQUEST),

	ETHNICITY_CREATE_PROFILE_REQUEST_LARGE("Size.createCustomerProfileRequest.ethnicity",
		"Ethnicity can be of maximum {0} characters long.", BAD_REQUEST),


	FIRST_NAME_UPDATE_PROFILE_REQUEST_EMPTY("NotBlank.updateProfileRequest.firstName",
		"First Name is required.", BAD_REQUEST),

	FIRST_NAME_UPDATE_PROFILE_REQUEST_LARGE("Size.updateProfileRequest.firstName",
		"First Name can be of maximum {0} characters long.", BAD_REQUEST),

	LAST_NAME_UPDATE_PROFILE_REQUEST_EMPTY("NotBlank.updateProfileRequest.lastName",
		"Last Name is required.", BAD_REQUEST),

	LAST_NAME_UPDATE_PROFILE_REQUEST_LARGE("Size.updateProfileRequest.lastName",
		"Last Name can be of maximum {0} characters long.", BAD_REQUEST),

	EMAIL_UPDATE_PROFILE_REQUEST_EMPTY("NotBlank.updateProfileRequest.email", "Email is required.",
		BAD_REQUEST),

	EMAIL_UPDATE_PROFILE_REQUEST_LARGE("Size.updateProfileRequest.email",
		"Email can be of maximum {0} characters long.", BAD_REQUEST),

	EMAIL_UPDATE_PROFILE_REQUEST_INVALID("Email.updateProfileRequest.email",
		"Provided email address is invalid.", BAD_REQUEST),

	PHONE_UPDATE_PROFILE_REQUEST_EMPTY("NotBlank.updateProfileRequest.email",
		"Email or phone is required.", BAD_REQUEST),

	PHONE_UPDATE_PROFILE_REQUEST_LARGE("Size.updateProfileRequest.phone",
		"Phone can be of maximum {0} characters long.", BAD_REQUEST),

	PHONE_UPDATE_PROFILE_REQUEST_INVALID("NotValid.updateProfileRequest.phone",
		"Provided phone number is invalid.", BAD_REQUEST),

	TIME_ZONE_UPDATE_PROFILE_REQUEST_LARGE("Size.updateProfileRequest.timeZone",
		"Time Zone can be of maximum {0} characters long.", BAD_REQUEST),

	TIME_ZONE_UPDATE_PROFILE_REQUEST_INVALID("NotValid.updateProfileRequest.timeZone",
		"Provided Time Zone is invalid.", BAD_REQUEST),


	EMAIL_ALREADY_EXISTS("error.email.already.exists",
		"Email : {0} is already registered, please provide different Email or Login.", CONFLICT),

	INVALID_DATE_TYPE("error.invalid.datetype", "Selected Datetype is invalid.", BAD_REQUEST),

	USER_NAME_NOT_FOUND("error.user.name.not.found", "The {0}, is not found for the user.", BAD_REQUEST),

	USER_NOT_FOUND("error.user.not.found", "No user found by ID : {0}", NOT_FOUND),

	USER_NOT_FOUND_BY_EMAIL("error.user.not.found.by.email", "No user found by Email : {0}",
		NOT_FOUND),

	USER_NOT_FOUND_BY_PHONE_OR_EMAIL("error.user.not.found.by.phone.email",
		"No user found by Phone or Email", NOT_FOUND),

	ACTIVE_USER_NOT_FOUND("error.active.user.not.found", "No active user found by ID : {0}",
		NOT_FOUND),

	CREATE_USER_PROFILE("error.create.user.profile",
		"Some error occurred while creating User Profile!", INTERNAL_SERVER_ERROR),

	UPDATE_USER_PROFILE("error.update.user.profile",
		"Some error occurred while updating User Profile!", INTERNAL_SERVER_ERROR),

	UPDATE_USER_PASSWORD_INVALID_CURRENT_PASSWORD(
		"error.update.user.password.invalid.current.password",
		"Provided current password is invalid!", BAD_REQUEST),

	UPDATE_USER_PASSWORD("error.update.user.password",
		"Some error occurred while updating User Password!", INTERNAL_SERVER_ERROR),

	CUSTOMER_ALREADY_REGISTERED("error.create.customer.profile.already.exists",
		"Customer already exists by provided email or phone!", CONFLICT),

	USER_UNAUTHORIZED_TO_CHANGE_PASSWORD("user.unauthorized.change.password",
		"A Azure AD user password is not authorized to change.", INTERNAL_SERVER_ERROR),

	EMAIL_FORGOT_PASSWORD_REQUEST_EMPTY("NotBlank.forgotPasswordRequest.email",
		"Email or Phone is required.", BAD_REQUEST),

	EMAIL_FORGOT_PASSWORD_REQUEST_INVALID("Email.forgotPasswordRequest.email",
		"Provided email address is invalid.", BAD_REQUEST),

	PHONE_FORGOT_PASSWORD_REQUEST_INVALID("NotValid.forgotPasswordRequest.phone",
		"Provided phone number is invalid.", BAD_REQUEST),

	ERROR_FORGOT_PASSWORD("error.forgot.password",
		"Error occurred in forgot password process. Please try again.", INTERNAL_SERVER_ERROR),

	TOKEN_RESET_PASSWORD_REQUEST_EMPTY("NotBlank.resetPasswordRequest.token", "Token is required.",
		BAD_REQUEST),

	PASSWORD_RESET_PASSWORD_REQUEST_EMPTY("NotBlank.resetPasswordRequest.newPassword",
		"Password is required.", BAD_REQUEST),

	CONFIRM_PASSWORD_RESET_PASSWORD_REQUEST_EMPTY("NotBlank.resetPasswordRequest.confirmNewPassword",
		"Confirm Password is required.", BAD_REQUEST),

	PASSWORD_CONFIRM_PASSWORD_RESET_PASSWORD_REQUEST_NOT_MATCH(
		"NotMatch.resetPasswordRequest.newPassword",
		"Password and Confirm Password are not matching.", BAD_REQUEST),

	PASSWORD_RESET_PASSWORD_REQUEST_LARGE("Size.resetPasswordRequest.newPassword",
		"Password can be of maximum {0} characters long.", BAD_REQUEST),

	CONFIRM_PASSWORD_RESET_PASSWORD_REQUEST_LARGE("Size.resetPasswordRequest.confirmNewPassword",
		"Confirm Password can be of maximum {0} characters long.", BAD_REQUEST),

	ERROR_RESET_PASSWORD_USER_NOT_FOUND("error.reset.password.not.found",
		"You have not initiated forgot password process.", NOT_FOUND),

	RESET_PASSWORD_LINK_EXPIRED("error.reset.password.link.expired",
		"Reset password link is expired. Please initiate the process agian!", BAD_REQUEST),

	ERROR_RESET_PASSWORD("error.reset.password",
		"Error occurred while resetting your password. Please try again.", INTERNAL_SERVER_ERROR),

	CONFIG_REQUEST_KEY_BLANK("NotBlank.configRequest.key", "Key is required.", BAD_REQUEST),

	CONFIG_REQUEST_VALUE_BLANK("NotBlank.configRequest.value", "Value is required.", BAD_REQUEST),

	KEY_CONFIG_REQUEST_SIZE("Size.configRequest.key", "Key can be of maximum {0} characters long.",
		BAD_REQUEST),

	ACCESS_TYPE_CONFIG_REQUEST_NULL("NotNull.configRequest.accessType", "AccessType is required.",
		HttpStatus.BAD_REQUEST),

	CREATE_CONFIG("error.create.config",
		"Error occurred while creating new Config. Please try again.", INTERNAL_SERVER_ERROR),

	CONFIG_NOT_FOUND("error.config.not.found", "No config found by ID : {0}.", NOT_FOUND),

	CONFIG_NOT_ACTIVE("error.config.not.active", "Config with ID : {0} is not active.", NOT_FOUND),

	UPDATE_CONFIG("error.update.config", "Error occurred while updating config. Please try again.",
		INTERNAL_SERVER_ERROR),

	DELETE_CONFIG("error.delete.config", "Error occurred while deleting config. Please try again.",
		INTERNAL_SERVER_ERROR),

	CONFIG_ALREADY_EXISTS("error.config.already.exist", "Config by this name is already exist.",
		INTERNAL_SERVER_ERROR),

	STRIPE_CONFIGURATION_NOT_FOUND("error.stripe.configuration.not.found",
		"Stripe configuration not found!", NOT_FOUND),


	SEND_EMAIL("error.send.email", "Error occurred while sending Email. Reason : {0}",
		INTERNAL_SERVER_ERROR),

	SEND_SMS("error.send.sms", "Error occurred while sending SMS. Reason : {0}",
		INTERNAL_SERVER_ERROR),

	NOTIFICATION_NOT_FOUND("error.notification.not.found", "No notification found by ID : {0}",
		NOT_FOUND),

	NOTIFICATION_ERROR("error.notification.send", "Error sending notifications : {0} ",
		INTERNAL_SERVER_ERROR),


	CLIENT_NOT_FOUND("error.client.not.found", "No client found by ID : {0}", NOT_FOUND),


	STREET_ADDRESS_REQUEST_EMPTY("NotBlank.addressRequest.street", "Street Address is required.",
		BAD_REQUEST),

	STREET_ADDRESS_REQUEST_LARGE("Size.addressRequest.street",
		"Street Address can be of maximum {0} characters long.", BAD_REQUEST),

	CITY_ADDRESS_REQUEST_EMPTY("NotBlank.addressRequest.city", "City is required.", BAD_REQUEST),

	CITY_ADDRESS_REQUEST_LARGE("Size.addressRequest.city",
		"City can be of maximum {0} characters long.", BAD_REQUEST),

	STATE_ADDRESS_REQUEST_EMPTY("NotBlank.addressRequest.state", "State is required.", BAD_REQUEST),

	STATE_ADDRESS_REQUEST_LARGE("Size.addressRequest.state",
		"State can be of maximum {0} characters long.", BAD_REQUEST),

	COUNTRY_CODE_ADDRESS_REQUEST_EMPTY("NotBlank.addressRequest.country", "Country is required.",
		BAD_REQUEST),

	COUNTRY_CODE_ADDRESS_REQUEST_LARGE("Size.addressRequest.country",
		"Country can be of maximum {0} characters long.", BAD_REQUEST),

	ZIP_CODE_ADDRESS_REQUEST_EMPTY("NotBlank.addressRequest.zip", "Zip Code is required.",
		BAD_REQUEST),

	ZIP_CODE_ADDRESS_REQUEST_LARGE("Size.addressRequest.zip",
		"Zip Code can be of maximum {0} characters long.", BAD_REQUEST),

	CLIENT_NAME_CLIENT_REQUEST_EMPTY("NotBlank.clientRequest.clientName", "Client Name is required.",
		BAD_REQUEST),

	CLIENT_NAME_CLIENT_REQUEST_LARGE("Size.clientRequest.clientName",
		"Client Name can be of maximum {0} characters long.", BAD_REQUEST),

	CONTACT_PERSON_CLIENT_REQUEST_EMPTY("NotBlank.clientRequest.contactPerson",
		"Contact Person is required.", BAD_REQUEST),

	CONTACT_PERSON_CLIENT_REQUEST_LARGE("Size.clientRequest.contactPerson",
		"Contact Person can be of maximum {0} characters long.", BAD_REQUEST),

	CONTACT_EMAIL_CLIENT_REQUEST_EMPTY("NotBlank.clientRequest.contactEmail",
		"Contact Email is required.", BAD_REQUEST),

	CONTACT_EMAIL_CLIENT_REQUEST_LARGE("Size.clientRequest.contactEmail",
		"Contact Email can be of maximum {0} characters long.", BAD_REQUEST),

	CONTACT_PHONE_CLIENT_REQUEST_EMPTY("NotBlank.clientRequest.contactPhone",
		"Contact Phone is required.", BAD_REQUEST),

	CONTACT_PHONE_CLIENT_REQUEST_LARGE("Size.clientRequest.contactPhone",
		"Contact Phone can be of maximum {0} characters long.", BAD_REQUEST),

	DUPLICATE_CLIENT_NAME("error.duplicate.client.name",
		"Client with provided name already exists in the system", CONFLICT),

	CLIENT_EXPORT("error.export.client", "Some error occurred while exporting Clients. Reason : {0}",
		INTERNAL_SERVER_ERROR),

	TRAILER_AUDIT_EXPORT("error.export.trailer_audit", "Some error occurred while exporting Clients. Reason : {0}",
		INTERNAL_SERVER_ERROR),

	LOCATION_NAME_LOCATION_REQUEST_EMPTY("NotBlank.locationRequest.locationName",
		"Location Name is required.", BAD_REQUEST),

	LOCATION_NAME_LOCATION_REQUEST_LARGE("Size.locationRequest.locationName",
		"Location Name can be of maximum {0} characters long.", BAD_REQUEST),

	LATITUDE_LOCATION_REQUEST_NULL("NotNull.locationRequest.latitude", "Latitude is required.",
		BAD_REQUEST),

	LONGITUDE_LOCATION_REQUEST_NULL("NotNull.locationRequest.longitude", "Longitude is required.",
		BAD_REQUEST),

	DUPLICATE_LOCATION_NAME("error.duplicate.location.name",
		"Location with provided name already exists in the system", CONFLICT),

	LOCATION_NOT_FOUND("error.location.not.found", "No location found by ID : {0}", NOT_FOUND),

	LOCATION_EXPORT("error.export.location",
		"Some error occurred while exporting Locations. Reason : {0}", INTERNAL_SERVER_ERROR),

	LOCATION_MAP_IMAGE_UPLOAD("error.upload.location.map.image",
		"Some error occurred while uploading location map image. Reason : {0}",
		INTERNAL_SERVER_ERROR),

	DELETE_LOCATION_IS_DEFAULT("error.delete.location.is.default",
		"Default location cannot be deleted or archived!", BAD_REQUEST),


	LOCATION_ID_SPOT_REQUEST_EMPTY("NotBlank.spotRequest.locationId", "Location ID is required.",
		BAD_REQUEST),

	SPOT_NAME_SPOT_REQUEST_EMPTY("NotBlank.spotRequest.spotName", "Spot Name is required.",
		BAD_REQUEST),

	SPOT_NAME_SPOT_REQUEST_LARGE("Size.spotRequest.spotName",
		"Spot Name can be of maximum {0} characters long.", BAD_REQUEST),

	TYPE_SPOT_REQUEST_NULL("NotNull.spotRequest.type", "Type is required.", BAD_REQUEST),

	STATUS_SPOT_REQUEST_NULL("NotNull.spotRequest.status", "Status is required.", BAD_REQUEST),

	LATITUDE_SPOT_REQUEST_NULL("NotNull.spotRequest.latitude", "Latitude is required.", BAD_REQUEST),

	LONGITUDE_SPOT_REQUEST_NULL("NotNull.spotRequest.longitude", "Longitude is required.",
		BAD_REQUEST),

	DUPLICATE_SPOT_NAME("error.duplicate.spot.name",
		"Spot with provided name already exists in the system", CONFLICT),

	SPOT_NOT_FOUND("error.spot.not.found", "No spot found by ID : {0}", NOT_FOUND),

	SPOT_EXPORT("error.export.spot", "Some error occurred while exporting Spots. Reason : {0}",
		INTERNAL_SERVER_ERROR),


	PLATE_NUMBER_FLEET_REQUEST_EMPTY("NotBlank.fleetRequest.plateNumber", "Plate Number is required.",
		BAD_REQUEST),

	PLATE_NUMBER_FLEET_REQUEST_LARGE("Size.fleetRequest.plateNumber",
		"Plate Number can be of maximum {0} characters long.", BAD_REQUEST),

	YEAR_FLEET_REQUEST_NULL("NotNull.fleetRequest.year", "Year is required.", BAD_REQUEST),

	MAKE_FLEET_REQUEST_EMPTY("NotBlank.fleetRequest.make", "Make is required.", BAD_REQUEST),

	MAKE_FLEET_REQUEST_LARGE("Size.fleetRequest.make", "Make can be of maximum {0} characters long.",
		BAD_REQUEST),

	MODEL_FLEET_REQUEST_EMPTY("NotBlank.fleetRequest.model", "Model is required.", BAD_REQUEST),

	MODEL_FLEET_REQUEST_LARGE("Size.fleetRequest.model",
		"Model can be of maximum {0} characters long.", BAD_REQUEST),

	COLOR_FLEET_REQUEST_EMPTY("NotBlank.fleetRequest.color", "Color is required.", BAD_REQUEST),

	COLOR_FLEET_REQUEST_LARGE("Size.fleetRequest.color",
		"Color can be of maximum {0} characters long.", BAD_REQUEST),

	CARRIER_FLEET_REQUEST_EMPTY("NotBlank.fleetRequest.carrier", "Carrier is required.", BAD_REQUEST),

	CARRIER_FLEET_REQUEST_LARGE("Size.fleetRequest.carrier",
		"Carrier can be of maximum {0} characters long.", BAD_REQUEST),

	TYPE_FLEET_REQUEST_NULL("NotNull.fleetRequest.type", "Type is required.", BAD_REQUEST),

	UNIT_NUMBER_FLEET_REQUEST_EMPTY("NotBlank.fleetRequest.unitNumber", "Unit Number is required.",
		BAD_REQUEST),

	UNIT_NUMBER_REQUEST_LARGE("Size.fleetRequest.unitNumber",
		"Unit Number can be of maximum {0} characters long.", BAD_REQUEST),

	DUPLICATE_FLEET_UNIT_NUMBER("error.duplicate.fleet.unit.number",
		"Fleet with provided unit number already exists in the system!", CONFLICT),

	DUPLICATE_TRAILER("error.invalid.trailer.number", "Invalid Unit/Trailer #. Please verify and enter the correct trailer/unit #", CONFLICT),

	FLEET_NOT_FOUND("error.fleet.not.found", "No fleet found by ID : {0}", NOT_FOUND),

	FLEET_EXPORT("error.export.fleet", "Some error occurred while exporting Fleets. Reason : {0}",
		INTERNAL_SERVER_ERROR),

	FLEET_ENTRY_EXIT_EXPORT("error.export.fleet.export.import",
		"Some error occurred while exporting Fleet Entru Exits. Reason : {0}", INTERNAL_SERVER_ERROR),


	FLEET_IDS_ASSIGN_FLEET_REQUEST_EMPTY("NotEmpty.clientFleetRequest.fleetIds",
		"Fleet IDs are required.", BAD_REQUEST),

	CLIENT_ID_ASSIGN_FLEET_REQUEST_BLANK("NotBlank.clientFleetRequest.clientId",
		"Client ID is required.", BAD_REQUEST),

	FLEET_IDS_ASSIGN_FLEET_REQUEST_INVALID("Invalid.clientFleetRequest.fleetIds",
		"Provided Fleet IDs are not valid.", BAD_REQUEST),


	ASSIGNED_TO_JOB_REQUEST_BLANK("NotBlank.jobRequest.assignedToUserId",
		"Assigned to User ID is required.", BAD_REQUEST),

	FLEET_ID_JOB_REQUEST_BLANK("NotBlank.jobRequest.fleetId", "Fleet ID is required.", BAD_REQUEST),

	PICKUP_LOCATION_ID_JOB_REQUEST_BLANK("NotBlank.jobRequest.pickupLocationId",
		"Pickup Location ID is required.", BAD_REQUEST),

	PICKUP_SPOT_ID_JOB_REQUEST_BLANK("NotBlank.jobRequest.pickupSpotId",
		"Pickup Spot ID is required.", BAD_REQUEST),

	FLEET_STATUS_JOB_REQUEST_NULL("NotNull.jobRequest.fleetStatus", "Fleet Status is required.",
		BAD_REQUEST),

	PRIORITY_JOB_REQUEST_NULL("NotNull.jobRequest.priority", "Priority is required.", BAD_REQUEST),

	DRIVER_JOB_CREATE_UPDATE_ACCESS("error.driver.job.create.access",
		"DRIVER can only create / update job for himself!", FORBIDDEN),

	SPOTTER_JOB_UPDATE_ACCESS("error.spotter.job.update.access",
		"SPOTTER can only update job created by him/her!", FORBIDDEN),

	JOB_NOT_FOUND("error.job.not.found", "No job found by ID : {0}", NOT_FOUND),

	JOB_EXPORT("error.export.job", "Some error occurred while exporting Jobs. Reason : {0}",
		INTERNAL_SERVER_ERROR),


	FIRST_NAME_USER_REQUEST_EMPTY("NotBlank.userRequest.firstName", "First Name is required.",
		BAD_REQUEST),

	FIRST_NAME_USER_REQUEST_LARGE("Size.userRequest.firstName",
		"First Name can be of maximum {0} characters long.", BAD_REQUEST),

	LAST_NAME_USER_REQUEST_EMPTY("NotBlank.userRequest.lastName", "Last Name is required.",
		BAD_REQUEST),

	LAST_NAME_USER_REQUEST_LARGE("Size.userRequest.lastName",
		"Last Name can be of maximum {0} characters long.", BAD_REQUEST),

	EMAIL_USER_REQUEST_EMPTY("NotBlank.userRequest.email", "Email is required.", BAD_REQUEST),

	EMAIL_USER_REQUEST_LARGE("Size.userRequest.email", "Email can be of maximum {0} characters long.",
		BAD_REQUEST),

	EMAIL_USER_REQUEST_INVALID("Email.userRequest.email", "Email is invalid.", BAD_REQUEST),

	PHONE_USER_REQUEST_LARGE("Size.userRequest.phone", "Phone can be of maximum {0} characters long.",
		BAD_REQUEST),

	TIME_ZONE_USER_REQUEST_LARGE("Size.userRequest.timeZone",
		"Time Zone can be of maximum {0} characters long.", BAD_REQUEST),

	TIME_ZONE_USER_REQUEST_INVALID("NotValid.userRequest.timeZone", "Time Zone is invalid.",
		BAD_REQUEST),

	CREATE_UPDATE_USER_DUPLICATE_EMAIL("error.create.update.user.duplicate.email",
		"User with provided email already exists in the system", CONFLICT),

	UPDATE_DELETE_SELF_USER("error.update.delete.self.user",
		"You cannot delete your own account : {0}", NOT_FOUND),

	USER_EXPORT("error.export.user", "Some error occurred while exporting Users. Reason : {0}",
		INTERNAL_SERVER_ERROR),


	STATUS_JOB_STATUS_REQUEST_NULL("NotNull.jobStatusRequest.status", "Job Status is required.",
		BAD_REQUEST),

	STATUS_DATE_TIME_JOB_STATUS_REQUEST_INVALID("NotValid.jobStatusRequest.statusDateTime",
		"Provided Status Date time is invalid.", BAD_REQUEST),

	STATUS_UPDATE_JOB_STATUS_INVALID("NotValid.jobStatusRequest.status",
		"Provided Status is not valid. Valid status are : IN_TRANSIT / COMPLETED.", BAD_REQUEST),


	FLEET_ID_FLEET_ENTRY_EXIT_REQUEST_EMPTY("NotBlank.fleetEntryExitRequest.clientId",
		"Fleet ID is required.", BAD_REQUEST),

	TYPE_FLEET_ENTRY_EXIT_REQUEST_NULL("NotNull.fleetEntryExitRequest.type", "Type is required.",
		BAD_REQUEST),


	TO_USER_IDS_MESSAGE_REQUEST_EMPTY("NotEmpty.messageRequest.toUserIds", "To User(s) is required.",
		BAD_REQUEST),

	MESSAGE_BODY_MESSAGE_REQUEST_EMPTY("NotBlank.messageRequest.messageBody",
		"Message Body is required.", BAD_REQUEST),

	TYPE_MESSAGE_REQUEST_NULL("NotNull.messageRequest.type", "Type is required.", BAD_REQUEST),

	MESSAGE_NOT_FOUND("error.message.not.found", "No message found by ID : {0}", NOT_FOUND),

	STATUS_MESSAGE_STATUS_REQUEST_NULL("NotNull.messageStatusRequest.status", "Status is required.",
		BAD_REQUEST),

	CREATE_MESSAGE_NO_TO_USER("error.create.message.no.to.users",
		"Provided To User Ids are invalid or all to users are inactive!", BAD_REQUEST),

	UNKNOWN_PREDICATE("error.unknown.predicate", "Provided predicate is not valid for search!",
		INTERNAL_SERVER_ERROR),

	INVALID_DATE_TIME_FORMAT("error.invalid.date.time.format",
		"Provided date time is invalid. Valid format is : {0}", BAD_REQUEST),


	KEY_PROPERTY_REQUEST_BLANK("NotBlank.propertyRequest.key", "Key is required.", BAD_REQUEST),

	VALUE_PROPERTY_REQUEST_BLANK("NotBlank.propertyRequest.value", "Value is required.", BAD_REQUEST),

	KEY_PROPERTY_REQUEST_SIZE("Size.propertyRequest.key",
		"Key can be of maximum {0} characters long.", BAD_REQUEST),

	ACCESS_TYPE_PROPERTY_REQUEST_NULL("NotNull.propertyRequest.accessType", "AccessType is required.",
		HttpStatus.BAD_REQUEST),

	DATA_TYPE_PROPERTY_REQUEST_NULL("NotNull.propertyRequest.propertyType",
		"PropertyType is required.", HttpStatus.BAD_REQUEST),

	PROPERTY_ALREADY_EXISTS("error.property.already.exist", "Property by this name is already exist.",
		INTERNAL_SERVER_ERROR),

	PROPERTY_NOT_FOUND("error.property.not.found", "No Property found by ID : {0}", NOT_FOUND),


	CHANNEL_NOTIFICATION_REQUEST_NULL("NotNull.notificationRequest.channel", "Channel is required.",
		BAD_REQUEST),


	MESSAGE_BODY_NOTIFICATION_REQUEST_EMPTY("NotBlank.notificationRequest.messageBody",
		"Message body is required.", BAD_REQUEST),


	MESSAGE_TITLE_NOTIFICATION_REQUEST_EMPTY("NotBlank.notificationRequest.messageTitle",
		"Message title is required.", BAD_REQUEST),


	START_TIME_NOTIFICATION_REQUEST_INVALID("NotValid.notificationRequest.startTime",
		"Start time is invalid.", BAD_REQUEST),


	REGISTRATION_ID_REGISTER_USER_DEVICE_REQUEST_EMPTY(
		"NotBlank.registerUserDeviceRequest.deviceRegistrationId",
		"Device registration ID is required.", BAD_REQUEST),

	REGISTRATION_ID_REGISTER_USER_DEVICE_REQUEST_LARGE(
		"Size.registerUserDeviceRequest.deviceRegistrationId",
		"Device registration ID can be of maximum {0} characters long.", BAD_REQUEST),

	DEVICE_NAME_REGISTER_USER_DEVICE_REQUEST_LARGE("Size.registerUserDeviceRequest.deviceName",
		"Device name can be of maximum {0} characters long.", BAD_REQUEST),

	DEVICE_MODEL_REGISTER_USER_DEVICE_REQUEST_LARGE("Size.registerUserDeviceRequest.deviceModel",
		"Device model can be of maximum {0} characters long.", BAD_REQUEST),

	DEVICE_TYPE_REGISTER_USER_DEVICE_REQUEST_NULL("NotNull.registerUserDeviceRequest.deviceType",
		"Device Type is required.", BAD_REQUEST),

	CREATE_USER_DEVICE("error.create.user.device", "Some error occurred while creating User Device",
		INTERNAL_SERVER_ERROR),


	CREATE_UPDATE_JOB_INVALID_ASSIGNED_TO("error.create.update.job.invalid.assigned.to",
		"Spot can only be assigned to SPOTTER or DRIVER or SUPERVISOR", BAD_REQUEST),
	
	CREATE_UPDATE_JOB_INVALID_SPOTTER_LOCATION("error.create.update.job.invalid.spotter.location",
			"Pickup location and drop location should be same if job is assigned to spotter", BAD_REQUEST),

	DEACTIVATE_FLEET_JOB_ASSOCIATION("error.deactivate.fleet.job.association.found",
		"Cannont deactivate this fleet, as this is associated with active jobs: {0}",
		INTERNAL_SERVER_ERROR),

	DELETE_FLEET_JOB_ASSOCIATION("error.deactivate.fleet.job.association.found",
		"Cannont delete this fleet, as this is associated with jobs, spots or clients: {0}",
		INTERNAL_SERVER_ERROR),

	DELETE_JOB("error.delete.job", "Cannont delete completed job.: {0}", INTERNAL_SERVER_ERROR),

	GUARD_ENTRY_EXIT_NOT_FOUND("error.guard.entry.exit.not.found",
		"No guard entry exit found by ID : {0}", NOT_FOUND),

	MANAGE_USER_OTHER_CLIENT_ACCESS_DENIED("error.manage.user.other.client.access.denied",
		"You are not authorized to manage users of other Clients.", FORBIDDEN),

	MANAGE_USER_OTHER_ROLE_ACCESS_DENIED("error.manage.user.other.role.access.denied",
		"You are authorized to manage users of roles DRIVER, GUARD and SPOTTER only.", FORBIDDEN),
	
	FORCE_UPDATION_ERROR("error.force.update","Error occured while getting force updation status",INTERNAL_SERVER_ERROR),
	
	DELETE_JOB_CREATED_FROM_MESSAGE("error.delete.job", "Spots that created from message cannot be deleted.", INTERNAL_SERVER_ERROR),
	
	DELETE_SPOT_WITH_ACTIVE_JOB("error.delete.spot", "The docks/parking spots cannot be deleted. Spot associated with the particular location is assigned to a driver.", INTERNAL_SERVER_ERROR),
	
	DELETE_LOCATION_WITH_ACTIVE_JOB("error.delete.location", "The locations cannot be deleted. Location is assigned to a driver.", INTERNAL_SERVER_ERROR),
	
	DELETE_SPOT_WITH_ACTIVE_TRAILER("error.delete.location", "The docks/particular spots cannot be deleted. Trailer assigned to the particular spot/dock.", INTERNAL_SERVER_ERROR),
	
	DUPLICATE_CARRIER("error.duplicate.carrier", "Carrier with provided name already exists in the system", CONFLICT),
	
	DUPLICATE_SUPPLIER("error.duplicate.supplier", "Supplier with provided name already exists in the system", CONFLICT),
	
	CARRIER_NOT_FOUND("error.carrier.not.found", "No carrier found by ID : {0}", NOT_FOUND),

	SUPPLIER_NOT_FOUND("error.supplier.not.found", "No supplier found by ID : {0}", NOT_FOUND),
	
	VALIDATION_ERROR("", "", BAD_REQUEST),
	
	OVERTIME_ERROR("error.overtime.duplicate","User is already on overtime", CONFLICT),
	
	ADD_CLIENT_OVERTIME("error.add.client.overtime", "Add a valid over time for the client", CONFLICT);

	private String code;
	private String message;
	private HttpStatus httpStatus;

	ErrorCode(String code, String message, HttpStatus httpStatus) {
		this.code = code;
		this.message = message;
		this.httpStatus = httpStatus;
	}
}

