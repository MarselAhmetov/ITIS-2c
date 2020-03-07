package model.momento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.History;
import model.Page;
import service.Browser;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomentoBrowser implements Momento {

    private Browser browser;

    private Page currentStage;
    private History history;


    public void recover() {

    }
}
