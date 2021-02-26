package moe.ning.prototype;

/**
 * CookieMachine
 *
 * @author telnetning
 * @since 2021-02-25
 */

/** As Prototype Class **/
class Cookie implements Cloneable {
    @Override protected Object clone() throws CloneNotSupportedException
    {
        return (Cookie) super.clone();
    }
}

class CookieType01 extends Cookie {}
class CookieType02 extends Cookie {}

public class CookieMachine
{
    private Cookie cookie;

    public CookieMachine(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie makeCookie() {
        try {
            return (Cookie) this.cookie.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        Cookie cookie01 = new CookieType01();
        Cookie cookie02 = new CookieType02();
        CookieMachine cm01 = new CookieMachine(cookie01);
        CookieMachine cm02 = new CookieMachine(cookie02);
        Cookie c1 = cm01.makeCookie();
        Cookie c2 = cm02.makeCookie();
        System.out.println(c1 instanceof Cookie);
        System.out.println(c2 instanceof Cookie);
        System.out.println(c1 instanceof CookieType01);
        System.out.println(c2 instanceof CookieType02);
    }
}
