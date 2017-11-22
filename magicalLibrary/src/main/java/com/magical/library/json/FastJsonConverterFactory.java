package com.magical.library.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Project: TShow
 * FileName: FastJsonConverterFactory.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:59 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:59 PM
 * Remark:
 */
public class FastJsonConverterFactory extends Converter.Factory {

    private Charset charset;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static FastJsonConverterFactory create() {
        return create(UTF_8);
    }

    public static FastJsonConverterFactory create(Charset charset) {
        return new FastJsonConverterFactory(charset);
    }

    public FastJsonConverterFactory(Charset charset) {
        this.charset = charset;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>(type, charset);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type, charset);
    }
}
