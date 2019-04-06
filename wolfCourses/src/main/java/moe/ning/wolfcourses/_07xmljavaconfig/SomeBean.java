package moe.ning.wolfcourses._07xmljavaconfig;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SomeBean
{
    private OtherBean otherBean;

    public void setOtherBean(OtherBean otherBean)
    {
        this.otherBean = otherBean;
    }

    public OtherBean getOtherBean()
    {
        return otherBean;
    }
}
