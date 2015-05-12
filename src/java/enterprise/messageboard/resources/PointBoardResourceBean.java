package enterprise.messageboard.resources;

import enterprise.messageboard.entities.Point;
import enterprise.messageboard.entities.User;
import enterprise.messageboard.exceptions.NotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
public class PointBoardResourceBean {

    @Context
    private UriInfo ui;
    @EJB
    PointHolderSingletonBean singleton;

    @GET
    @Produces({"text/json"})
    public List<Point> getPoints() {
        return singleton.getPoints();
    }

    @POST
    public Response addPoint(String msg) throws NotFoundException {
        String[] strArray = msg.split(",");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length-1; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        List <User> l = singleton.getUsers();
        for (User u:l)
        {
            if (u.getHash().equals(strArray[2]))
            {
                Point m = singleton.addPoint(intArray[0],intArray[1],u.getR(),u.getG(),u.getB(), u.getName());
                URI msgURI = ui.getRequestUriBuilder().path(Integer.toString(m.getUniqueId())).build();
                return Response.created(msgURI).build();
            }
        }
        throw new NotFoundException();

        
    }

    @Path("register")
    @POST
    @Produces({"text/json"})
    public User addUser(String msg) {
        User m = singleton.addUser(msg);
        return m;

    }
}
