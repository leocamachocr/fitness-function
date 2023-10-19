package ucr.ac.fitfun.security.api.types;

public record OkResponse(String status) {

    public static final OkResponse OK_RESPONSE = new OkResponse("ok");
}