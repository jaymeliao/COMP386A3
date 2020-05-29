package TaxDemo.com.tax.domain.elements;

// line 133 "../../../../../../../ump/tmp17w5ky53o/model.ump"
//line 339 "../../../../../../../ump/tmp17w5ky53o/model.ump"
public class Province
{

//------------------------
// MEMBER VARIABLES
//------------------------

//Province Attributes
private String name;

//------------------------
// CONSTRUCTOR
//------------------------

public Province(String aName)
{
 name = aName;
}

//------------------------
// INTERFACE
//------------------------

public boolean setName(String aName)
{
 boolean wasSet = false;
 name = aName;
 wasSet = true;
 return wasSet;
}

public String getName()
{
 return name;
}

public void delete()
{}


public String toString()
{
 return super.toString() + "["+
         "name" + ":" + getName()+ "]";
}
}