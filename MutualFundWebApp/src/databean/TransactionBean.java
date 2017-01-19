// Name: Ajinkya Nimbalkar Date: 5th December 2016 Course Number :08672

package databean;

import org.genericdao.MaxSize;
import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean {
	private int    transactionId;
	private int  customerId;
	private int    fundId;
	private String executeDate;
	private int shares;
	private int transactionType;
	private double amount;
	
	public int    getTransactionId()                { return transactionId;           }
    public int getCustomerId()              { return customerId;         }
    public int    getFundId()          { return fundId;     }
    public String getExecuteDate()         { return executeDate;    }
    public int getShares()          { return shares;     }
    public int getTransactionType()          { return transactionType;     }
    public double getAmount() 			{return amount;				}

    public void   setTransactionId(int i)           { transactionId = i;              }
	public void   setCustomerId(int i)      { customerId = i;            }
	public void   setFundId(int i)     { fundId = i;        }
	@MaxSize(50)
	public void   setExecuteDate(String s) { executeDate = s;       }
	public void   setShares(int i)  { shares = i;        }
	public void   setTransactionType(int i)  { transactionType = i;        }
}
