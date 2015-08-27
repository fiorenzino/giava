# Introduzione #

Oggetto Search e Paginzione.

# Dettagli #


Perchè le informazioni sulla paginazione si trovano all'interno dell'AbstactController
e non nell'oggetto Search.

Perchè i campi di AbstractController:

private int rowCount;
private int pageSize = PAGE\_SIZE;
private int rowsPerPage = ROWS\_PER\_PAGE;
private int scrollerPage = SCROLLER\_PAGE;

Non stanno tutti nella classe Search, visto che sono tutte informazioni per la costruzione delle query?