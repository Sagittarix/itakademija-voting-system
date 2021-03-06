package vs.admin_.candidate;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;

@Service
public class CandidateCSVParserService {

	private static final Logger log = Logger.getLogger(CandidateCSVParserService.class.getName());
	private CSVReader csvReader = null;
	private Reader stringReader;
	private char separator = ',';

	public CandidateCSVParserService() {
	}

	/**
	 * Overloaded method to pass variable number of parameters, to csvReader
	 * method.
	 * 
	 * @param toParse
	 * @return
	 */
	public List<String[]> csvReader(String toParse) {
		return csvReader(toParse, this.separator);
	}

	/**
	 * Method parses CSV formated String. Returns List of rows in which array of
	 * Strings contain row cells. Optional char separator can be used (by
	 * default <,>). Performs String.trim(), to remove whitespace from input
	 * String.
	 * 
	 * @param toParse
	 * @param separator
	 * @return
	 */
	public List<String[]> csvReader(String toParse, char separator) {
		log.debug("CandidateCSVParserService - csvReader started...");
		this.separator = separator;

		stringReader = new StringReader(toParse.trim());

		try {
			csvReader = new CSVReader(stringReader, this.separator);

			List<String[]> rows = csvReader.readAll();

			stringReader.close();
			log.debug("CandidateCSVParserService - csvReader finished!");
			return rows;

		} catch (Exception e) {
			log.error("CandidateCSVParserService - csvReader error " + e);
			e.printStackTrace();
			return null;
		} finally {
			try {
				stringReader.close();
			} catch (IOException e) {
				log.error("CandidateCSVParserService - csvReader  IO error " + e);
				e.printStackTrace();
			}
		}
	}
}
