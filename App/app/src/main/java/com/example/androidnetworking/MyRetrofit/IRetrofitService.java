package com.example.androidnetworking.MyRetrofit;

import com.example.androidnetworking.model.AccessToken;
import com.example.androidnetworking.model.Person;
import com.example.androidnetworking.model.Product;
import com.example.androidnetworking.model.Productcategory;
import com.example.androidnetworking.model.Response2pikModel;
import com.example.androidnetworking.model.ResponseModel;
import com.example.androidnetworking.model.book;
import com.example.androidnetworking.model.cart;
import com.example.androidnetworking.model.category;
import com.example.androidnetworking.model.diemsv;
import com.example.androidnetworking.model.login;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IRetrofitService {
    @GET("lab3.php")
    Call<List<Person>> get();

    @POST("lab3.php")
    Call<diemsv> postdiem(@Body diemsv diemsv);

    @POST("login.php")
    Call<AccessToken> login(@Body Person person);

    @POST("getProfile.php")
    Call<Person> getProfile();

    @POST("user_login.php")
    Call<login> login2(@Body login login);

    @POST("user_register.php")
    Call<login> SigUP(@Body login login);

    @POST("user_forgot_password.php")
    Call<login> Forgot(@Body login login);

    //get sản phẩm
    @POST("product_getAll.php")
    Call<List<Product>> getAllProduct();
    @POST("product_get1.php")
    Call<List<Product>> get1Product();
    @POST("product_get2.php")
    Call<List<Product>> get2Product();
    @POST("product_get3.php")
    Call<List<Product>> get3Product();
    @POST("product_get4.php")
    Call<List<Product>> get4Product();
    @POST("product_get5.php")
    Call<List<Product>> get5Product();
    @POST("product_get_cart.php")
    Call<List<cart>> getCart();
    @POST("Category_getAll.php")
    Call<List<category>> getCategory();

    //spiner
    @POST("product_categoryGetAll.php")
    Call<List<Productcategory>> getAllCategories();

    //CHụp hình
    @Multipart
    @POST("/")
    Call<Response2pikModel> upLoad2pik (@Part MultipartBody.Part image);
    //Insert
    @POST("product_insert.php")
    Call<ResponseModel> insert(@Body Product product);

    @POST("insert_category.php")
    Call<ResponseModel> insertCategory(@Body category category);

    @POST("product_insert_cart.php")
    Call<ResponseModel> insertCart(@Body cart cart);

    @POST("product_update.php")
    Call<ResponseModel> update(@Body Product product);

    //getID
    @POST("product_get_by_id.php")
    Call<Product> getProductById(@Body Product product);


    //delete
    @POST("product_delete.php")
    Call<ResponseModel> delete(@Body Product product);
    @POST("cart_delete.php")
    Call<ResponseModel> deleteCart(@Body cart cart);
    @POST("category_delete.php")
    Call<ResponseModel> deleteCategory(@Body category category);






    @POST("book_getAll.php")
    Call<List<book>> getAllBook();
    @POST("book_delete.php")
    Call<ResponseModel>deleteBook(@Body book book);


}
