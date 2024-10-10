package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtility {

	private Properties properties;
	private FileInputStream fileInputStream;
	private String filePath = "./externalResources/";
	private String fileName;		

	private void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public PropertiesUtility changeFileName(String fileName) throws IOException {
		
		this.fileName = fileName;
		setFileInputStream(new FileInputStream(filePath + "/" + fileName + ".properties"));
		properties.load(fileInputStream);
		return this;
	
	}
	
	public PropertiesUtility changeFilePath(String filePath) throws IOException {
		
		this.filePath = filePath;
		setFileInputStream(new FileInputStream(filePath + "/" + fileName + ".properties"));
		properties.load(fileInputStream);
		return this;
		
	}

	private FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	private void setProperties(Properties properties) {
		this.properties = properties;
	}

	private void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public PropertiesUtility load(String fileName) throws IOException {

		setProperties(new Properties());
		setFileName(fileName);
		setFileInputStream(new FileInputStream("./externalResources/" + fileName + ".properties"));
		properties.load(getFileInputStream());
		
		return this;

	}

	public PropertiesUtility load(String filePath, String fileName) throws IOException {

		setProperties(new Properties());
		setFilePath(filePath);
		setFileName(fileName);
		setFileInputStream(new FileInputStream(filePath + "/" + fileName + ".properties"));
		properties.load(getFileInputStream());
		
		return this;

	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public ArrayList<String> getValue(ArrayList<String> keys) {

		ArrayList<String> valuesList = new ArrayList<String>();
		Iterator<String> keyIterator = keys.iterator();

		while (keyIterator.hasNext())
			valuesList.add(keyIterator.next().toString());

		return valuesList;

	}

	public void print() {

		Iterator<String> keyIterator = getKeys().iterator();
		Iterator<String> valueIterator = getValues().iterator();

		while (keyIterator.hasNext() && valueIterator.hasNext())
			System.out.println(keyIterator.next() + " : " + valueIterator.next());

	}

	public int getSize() {
		
		return properties.size();
	
	}

	public ArrayList<String> getKeys() {

		ArrayList<String> keysList = new ArrayList<String>();
		Set<Object> keySet = properties.keySet();
		Iterator<Object> iterator = keySet.iterator();

		while (iterator.hasNext())
			keysList.add(iterator.next().toString());

		return keysList;

	}

	public ArrayList<String> getValues() {

		ArrayList<String> valuesList = new ArrayList<String>();
		Collection<Object> valueCollection = properties.values();
		Iterator<Object> valueIterator = valueCollection.iterator();

		while (valueIterator.hasNext())
			valuesList.add(valueIterator.next().toString());

		return valuesList;

	}

}