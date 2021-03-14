export class Order {
  id: string;
  state: string;
  checkIn: Date;
  checkOut: Date;
  userId: number;
  hotelId: number;
  hotelName: string;
  rooms:string[];
  totalAmount:number;
}
