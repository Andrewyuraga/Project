package web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import web.command.Controller;
import web.command.impl.*;

/**
 * Created by yslabko on 08/13/2017.
 */
@Getter
@AllArgsConstructor
public enum ControllerType {
    EMAIL("email.jsp", "Email", "email.title", new EmailController()),
    LOGOUT("email.jsp", "Logout", "logout.title", new LogoutController()),
    ORDERS("orders/ord.jsp", "Orders", "ord.title", new OrderController()),
    PRODUCTS("products/main.jsp", "Parts", "parts.title", new ProductController()),
    REGISTRATION_USER("email.jsp", "Registration", "register.title", new RegistrationUserController()),
    ADD_PARTS("partsAdd/partsAdd.jsp", "PartsAdd", "partsadd.title", new PartsAddController()),
    REVIEWS("reviews/reviews.jsp", "reviews", "reviews.title", new GetReviewsController()),
    DELETE_PARTS("getProduct/productAll.jsp", "deleteproduct", "deleteProduct.title", new DeleteProductController()),
    DELETE_ORD("orders/ord.jsp", "deleteord", "deleteord.title", new DeleteOrdController()),
    GET_TOTAL_SUM("orders/getTotal.jsp", "getTotal", "ord.title", new GetTotalSum()),
    GET_ALL_USER("getAllUser/getAllUser.jsp", "users", "users.title", new GetAllUserConroller()),
    CREATE_ORD("products/main.jsp", "createord", "ord.create", new CreateOrdConroller()),
    PRODUCT_OIL("products/productOil.jsp", "getOil", "parts.oil", new ProductOilController()),
    CREATE_REVIEW("reviews/reviews.jsp", "createReview", "reviews.title", new ReviewAdd()),
    PARTS_ALL("getProduct/productAll.jsp", "getAll", "partsall.title", new GetProductController()),
    DELETE_REVIEW("reviews/reviews.jsp", "deleteReview", "reviews.delete", new DeleteReviewController()),
    ORDER_ALL("orders/ordAll.jsp", "getAllord", "ord.getall", new GetAllOrd()),
    UPDATE_USER_ADDRESS("updateUserAddress/updateAddress.jsp", "updateAddress", "user.update", new UpdateAddress());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с продуктами
        return PRODUCTS;
    }
}