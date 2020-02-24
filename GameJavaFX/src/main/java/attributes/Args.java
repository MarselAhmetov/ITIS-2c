package attributes;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Args {
    @Parameter
    public List<String> parameters = new ArrayList<>();

    @Parameter(names = { "--address"})
    public String address = "127.0.0.1";

    @Parameter(names = { "--port"})
    public Integer port = 4321;

}
