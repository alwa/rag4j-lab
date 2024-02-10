package org.rag4j.rag.retrieval;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
 * Value object representing the output of a retrieval strategy. It contains a list of {@link RetrievalOutputItem}s.
 */
@Builder
@Getter
public class RetrievalOutput {
    private List<RetrievalOutputItem> items;

    @Builder
    @Getter
    public static class RetrievalOutputItem {
        private String documentId;
        private int chunkId;
        private String text;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RetrievalOutputItem that = (RetrievalOutputItem) o;

            if (chunkId != that.chunkId) return false;
            if (!Objects.equals(documentId, that.documentId)) return false;
            return Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            int result = documentId != null ? documentId.hashCode() : 0;
            result = 31 * result + chunkId;
            result = 31 * result + (text != null ? text.hashCode() : 0);
            return result;
        }
    }

    public String constructContext() {
        StringBuilder sb = new StringBuilder();
        for (RetrievalOutputItem item : items) {
            sb.append(item.getText());
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RetrievalOutput that = (RetrievalOutput) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

}
