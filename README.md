# Solution for Java Questions and SQL Questions

### 1. DecoratorStream - Implemented write() method.
     @Override
        public void write(byte[] b, int off, int len) throws IOException {
            /* Solution to the CODE TEST - REM */
            String convertedByte  = new String(b, "UTF-8");
            convertedByte = this.prefix + convertedByte;
            this.stream.write(convertedByte.getBytes(Charset.forName("UTF-8")));
        }
### 2. Folders - Implemented folderNames() method.
        public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {

                /* Solution to the CODE TEST - REM */
                ArrayList<String> list = new ArrayList<String>();

                try {
                    //read XML from the given string
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    InputSource is = new InputSource(new StringReader(xml));
                    Document doc = builder.parse(is);

                    //this will return a list of xml tags whose name is `folder`
                    NodeList hiList = doc.getElementsByTagName("folder");

                    //Iterate and Extract folder names starting with letter U and add them to List
                    for (int i = 0; i < hiList.getLength(); i++) {
                        Node child = hiList.item(i);
                        Node name = child.getAttributes().getNamedItem("name");
                        String contents = name.getTextContent();
                        if(contents.startsWith("u") || contents.startsWith("U")){
                            list.add(contents);
                        }
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return list;

                //throw new UnsupportedOperationException("Waiting to be implemented.");
            }
### 4. Spring AOP - Implemented Advice as required.
       @Around("@annotation(LogExecution)")
        public void loggingAdvice(JoinPoint jp) {
            String calledMethod = jp.toShortString();
            logger.log("calledMethod   "+calledMethod);
        }

# SQL Queries
### ITEMS AND SELLERS
    select i.name as "Item Name", 
    s.name as "Seller Name (Showing only Sellers with 4-star Rating)"
    from sellers s inner join items i 
    on s.id = i.sellerId
    where s.rating > 4;

### CREATE TABLE with Contraints
    CREATE TABLE users_roles (
    userId INTEGER NOT NULL,
    roleId INTEGER NOT NULL,
    PRIMARY KEY (roleId, userId),
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (roleId) REFERENCES roles(id)
);
