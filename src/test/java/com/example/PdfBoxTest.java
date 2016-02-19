package com.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class PdfBoxTest {
    @Test
    public void pdfbox_3233() throws Exception {
        byte[] data = StreamUtils.copyToByteArray(this.getClass().getClassLoader().getResourceAsStream("input.pdf"));

        PDAcroForm acroForm = PDDocument.load(data).getDocumentCatalog().getAcroForm();

        Map<String, String> fieldValuesByName = new LinkedHashMap<>();
        for (PDField f : acroForm.getFields()) {
            fieldValuesByName.put(f.getFullyQualifiedName(), f.getValueAsString());
        }

        for (PDField f : acroForm.getFields()) {
            f.setValue(fieldValuesByName.get(f.getFullyQualifiedName()));
        }
    }
}
