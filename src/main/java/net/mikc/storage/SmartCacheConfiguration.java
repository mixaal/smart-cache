package net.mikc.storage;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
public class SmartCacheConfiguration extends Configuration {
    private List<String> peers;
}
