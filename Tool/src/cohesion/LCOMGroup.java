package cohesion;

import metric_data_structure.OurMethod;

import java.util.LinkedHashSet;
import java.util.Set;

public class LCOMGroup {
    Set<OurMethod> methods = new LinkedHashSet<>();
    Set<String> vars = new LinkedHashSet<>();
}
