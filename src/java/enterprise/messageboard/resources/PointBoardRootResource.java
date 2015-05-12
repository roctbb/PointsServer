
package enterprise.messageboard.resources;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
@Path("/")
public class PointBoardRootResource {

    @EJB PointBoardResourceBean r;

    @Path("points")
    public PointBoardResourceBean getPointBoardResourceBean() {
        return r;
    }
}

