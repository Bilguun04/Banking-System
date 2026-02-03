export interface BankAccount {
  id?: number;
  accountNumber: string;
  accountHolder: string;
  balance: number;
  accountType: string;
  currency: string;
  active: boolean;
  branchId: number;
}
