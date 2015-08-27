http://seamframework.org/Community/EXTENDEDPersistenceContextProduces

http://thatjavathing.blogspot.com/2009/04/extended-persistence-context.html
interessante soluzione da provare...

http://azajava.blogspot.com/2009/09/introduction-i-decided-to-share-special.html
Questo articolo spiega molto bene la differenza tra persistence context extended e transactional persistence context.
La seconda soluzione non mi piace per niente (EAGER di ogni collezione)

Per quanto riguarda la prima soluzione, il discorso con @Version non mi dispiace: in ejb31. c'è da vedere come gestire accessi concorrenti alle risorse...

http://java.net/jira/browse/GLASSFISH-11805
interessanti le note al bug