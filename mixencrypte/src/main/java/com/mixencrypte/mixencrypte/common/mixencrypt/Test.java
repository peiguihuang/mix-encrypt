package com.mixencrypte.mixencrypte.common.mixencrypt;

/**
 * @author 黄培桂
 * @create 2018-12-07 10:13
 **/

public class Test {


    public static void main(String args[]) throws Exception {
        String publickeyA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDGLKDodE7Q1n0uZvHaQhgfuYbygUPMgIDymPV9MeTkOsDDcksZHzzZ9QiNJ5r5zWUZW155zXhf9oVlXiz50wnxSGdFkbUEqNgwQ74AbhOsqXhbGKmN1LuOuDwufPi3mZ3JnEJ4WnP2dzo2UkDZpqed1qSu1vNZGeLJSXbWjLXqUwIDAQAB";
        String privattkeyA = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMYsoOh0TtDWfS5m8dpCGB-5hvKBQ8yAgPKY9X0x5OQ6wMNySxkfPNn1CI0nmvnNZRlbXnnNeF_2hWVeLPnTCfFIZ0WRtQSo2DBDvgBuE6ypeFsYqY3Uu464PC58-LeZncmcQnhac_Z3OjZSQNmmp53WpK7W81kZ4slJdtaMtepTAgMBAAECgYBkwTvt83Q-6U4r0VsVRJVPS9w4X1BBIPRuYyYsyLk-8EAhHnLgCum9Rr9aRNCeQLct9S5LbxPMdmHL792eBpX-mEKBlUcMdTRzJlyMJKu-aA8dpIkIDUSRfrH2333Meey_CkMkKxX7nyjVQihuYXNkMXA7DLcsIIhEm0iBj0iygQJBAPlLV12E1wo6wlI3HkIj_38yJ1E_l_LaMmZ_zjH06D1-spOzSQcbWy7DXCg6nM7KiF_dKQHPuPlOg8If4eEIxRsCQQDLgUV8OEc3t11jTNd3qVZZEVxBjeHL5ubll9LKVWeMXJvN94CYanD0txw3Mg7KslKVigYSN8eHQLOlW0hTVZspAkEAn4-chKwQ42hGlk7XHChkUSptxfA5NmR4W-iztn_14d5lOGpJdLYKv7ylEM6rLSSu8wTgSVuGHnQlmyvC7MErPwJAc_KngGsgSKJ01wsFCOy2gfDeO4t3RxG8JLwoIZgfc12gd-24mx6qVQOvU9N1kg-f9gu6Ac8-I9W9hU1JHBH-YQJBAOubcZM5E_6i5HO5L3MJKKJr3DNU84S6OE7R-js--xws8m9jB5Qn7ga1Ls4myS-bb7997aFdBM55nllxr2gnXHc";

        String publickeyB = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQBL_hBDzjjxNh_R-wedjAQBfSYZiwmtV-JxFMXmXfLbkPkwPaTfxwtJ6JC_8xLZcWgv-TLO7MyYTiwV-U10DDcdZDOhtIqwLZj7rdor_QrQeg3zb_7TdvpckiICpZReeAjekz-duhDSXMQ4evJocxs-MU7ygfTQL7Oc2dNDX3QQIDAQAB";
        String privatekeyB = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJAEv-EEPOOPE2H9H7B52MBAF9JhmLCa1X4nEUxeZd8tuQ-TA9pN_HC0nokL_zEtlxaC_5Ms7szJhOLBX5TXQMNx1kM6G0irAtmPut2iv9CtB6DfNv_tN2-lySIgKllF54CN6TP526ENJcxDh68mhzGz4xTvKB9NAvs5zZ00NfdBAgMBAAECgYBDwPhEdCnxJK6JuvvdmnVOfNi4MRIUg8NMxjRtKTvj8y7NFO0LFwVcQJ0U_H_iMwCRpq92Hf16epP52d-NtnbQxtG4ySAYJmQiJwhkgqWkEg_FM3eexrsBiYZSJQVn6ba3FybgadE_RdPz-p2ORcW_2pt6HhXaQpC0H_PVWSDkAQJBAM_RK-qQ7ex_90J49opf4oRB2q3e7CGYbJeaPKQ51ZvmHP9ttDpoYoyZPel72o500DGuCoabx85Ta9MX_27-qrkCQQCxaN0qZt-KERglUK-bA4tjdb0h0725Euui6Ke_fzg2kjAHZFIKwwLLUPVSVAbIhz3E76d9RdzOK9nsjh26q0zJAkBehVTkK2cL2mgr47i-1z2_yfQcgPgFj75UdeJ_D48IiXFG_C6tIi0wpcJzH9IYWSkYb4B6GQCX53lzWcbkRRCBAkAvCjACSy6gbDHryv4U6xatc42Hh545kWO7WVcJNpFQHujvPNFoGxcFk9zITJFT4FAnhKfzVm_altP8nh4R3dPJAkBkX0970Aur425K6S9dq_SaCPGgI7e5tMj84yZxOVAaChcHDVLncdXDXoutij0VIgn9ia9w_yZ_hsSs_LZa5HVm";

        String m = "{\"a\":\"我是要被加密的密文！\"}";


        String m1 = MixEncryptUtils.encrypt(m,privattkeyA,publickeyB);
        System.out.println("加密后密文:" + m1);
        String m2 = MixEncryptUtils.decrypt(m1,privatekeyB,publickeyA);
        System.out.println(m2);
    }


}