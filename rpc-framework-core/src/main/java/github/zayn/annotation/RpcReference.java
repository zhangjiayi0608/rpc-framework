package github.zayn.annotation;

public @interface RpcReference {
    String version() default "";

    String group() default "";
}
