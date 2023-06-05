import java.io.File;
import java.util.Map;
import java.util.List;

public interface DataMasterInterface {

	boolean checkDataSet();

	void createDataset();

	void checkFile();

	Map<Character, Integer> loadData();

	List<Perfume> loadPerfume();

}
