package moe.ning.wolfcourses._05autocomponentscan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component("somebean")
public class SomeBean
{
    @Autowired
    private OtherBean otherBean;

    public void setOtherBean(OtherBean otherBean)
    {
        this.otherBean = otherBean;
    }

    public OtherBean getOtherBean(){
        return this.otherBean;
    }
}
