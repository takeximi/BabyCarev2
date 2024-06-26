/****** Object:  Table [dbo].[tblAccount]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAccount](
	[Username] [varchar](20) NOT NULL,
	[PasswordAcc] [varchar](32) NOT NULL,
	[UserID] [varchar](5) NOT NULL,
	[StatusAcc] [smallint] NOT NULL,
	[Code] [varchar](100) NULL, 
	[Email] [nvarchar](100) NULL,
 CONSTRAINT [PK__tblAccou__113489A2B652D786] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblAdmin]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAdmin](
	[AdminID] [varchar](5) NOT NULL,
	[FirstnameAd] [nvarchar](50) NOT NULL,
	[LastnameAd] [nvarchar](50) NOT NULL,
	[AddressAd] [nvarchar](100) NOT NULL,
	[Avatar] [nvarchar](100) ,
	[PhoneAd] [varchar](10) NOT NULL,
 CONSTRAINT [PK__tblAdmin__719FE4E855566EF7] PRIMARY KEY CLUSTERED 
(
	[AdminID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBill]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBill](
	[BillID] [varchar](20) NOT NULL,
	[EmployeeID] [varchar](5) NULL,
	[CTVID] [varchar](5) NULL,
	[CustomerID] [varchar](5) NULL,
	[AddressDelivery] [nvarchar](100) NULL,
	[DateCreate] [date] NULL,
	[PreferentialID] [varchar](10) NULL,
	[StatusBill] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[BillID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCalendar]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCalendar](
	[ShiftID] [varchar](5) NOT NULL,
	[ServiceID] [varchar](5) NOT NULL,
	[NumberOfResponses] [tinyint] NULL,
	[SetDay] [date] NOT NULL,
 CONSTRAINT [PK_tblShift] PRIMARY KEY CLUSTERED 
(
	[ShiftID] ASC,
	[ServiceID] ASC,
	[SetDay] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCTV]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCTV](
	[CTVID] [varchar](5) NOT NULL,
	[FirstnameCTV] [nvarchar](50) NOT NULL,
	[LastnameCTV] [nvarchar](50) NOT NULL,
	[AddressCTV] [nvarchar](100) NOT NULL,
	[Avatar] [nvarchar](100) ,
	[PhoneCTV] [varchar](10) NOT NULL,
 CONSTRAINT [PK__tblCTVto__A4AE64B8CB2439B5] PRIMARY KEY CLUSTERED 
(
	[CTVID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCustomer]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCustomer](
	[CustomerID] [varchar](5) NOT NULL,
	[FirstnameCus] [nvarchar](50) NOT NULL,
	[LastnameCus] [nvarchar](50) NOT NULL,
	[AddressCus] [nvarchar](100) NOT NULL,
	[Avatar] [nvarchar](100) ,
	[PhoneCus] [varchar](10) NOT NULL,
 CONSTRAINT [PK__tblCusto__A4AE64B8CB2439B5] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblEmployee]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblEmployee](
	[EmployeeID] [varchar](5) NOT NULL,
	[FirstnameEmp] [nvarchar](50) NOT NULL,
	[LastnameEmp] [nvarchar](50) NOT NULL,
	[AddressEmp] [nvarchar](100) NOT NULL,
	[Avatar] [nvarchar](100) ,
	[PhoneEmp] [varchar](10) NOT NULL,
 CONSTRAINT [PK__tblEmplo__7AD04FF1762FFD18] PRIMARY KEY CLUSTERED 
(
	[EmployeeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[BillID] [varchar](20) NOT NULL,
	[ProductID] [varchar](5) NOT NULL,
	[AmountProduct] [int] NULL,
	[PriceAtPuchase] [decimal](11, 2) NULL,
 CONSTRAINT [PK_tblOrderDetails] PRIMARY KEY CLUSTERED 
(
	[BillID] ASC,
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPreferential]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPreferential](
	[Preferential] [varchar](10) NOT NULL,
	[PreferentialName] [nvarchar](50) NULL,
	[StartDay] [date] NULL,
	[EndDay] [date] NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Preferential] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblProduct]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProduct](
	[ProductID] [varchar](5) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[ProductType] [nvarchar](20) NULL,
	[ProductPrice] [decimal](11, 2) NULL,
	[ProductImage] Text Null,
	[Amount] [int] NULL,
	[StatusProduct] [smallint] NULL,
 CONSTRAINT [PK__tblProduct__48E5380205E81EBD] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[tblService]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblService](
	[ServiceID] [varchar](5) NOT NULL,
	[ServiceName] [nvarchar](30) NULL,
	[ServicePrice] [decimal](11, 2) NULL,
	[StatusService] [nvarchar](30) NULL,
	[ServiceImage] Text Null,
	[Description] [nvarchar](200) NULL,
 CONSTRAINT [PK__tblServi__C51BB0EA7A480000] PRIMARY KEY CLUSTERED 
(
	[ServiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblServiceBill]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblServiceBill](
	[BillID] [varchar](20) NOT NULL,
	[EmployeeID] [varchar](5) NULL,
	[CustomerID] [varchar](5) NULL,
	[DateCreate] [date] NULL,
	[ShiftID] [varchar](5) NULL,
	[ServiceID] [varchar](5) NULL,
	[SetDay] [date] NULL,
	[StatusBill] [nvarchar](30) NULL,
	[Amount] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[BillID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[tblShift]    Script Date: 13/05/2024 10:37:04 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblShift](
	[ShiftID] [varchar](5) NOT NULL,
	[ShiftName] [nvarchar](30) NULL,
	[ShiftStartTime] [time](7) NULL,
	[ShiftEndTime] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[ShiftID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [unique_username]    Script Date: 13/05/2024 10:37:04 CH ******/
ALTER TABLE [dbo].[tblAccount] ADD  CONSTRAINT [unique_username] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblProductComment](
    [CommentID] INT IDENTITY(1,1) NOT NULL,
    [ProductID] VARCHAR(5) NOT NULL,
    [Comment] NVARCHAR(MAX) NOT NULL,
    CONSTRAINT [PK_tblProductComment] PRIMARY KEY CLUSTERED 
    (
        [CommentID] ASC
    ),
    CONSTRAINT [FK_ProductID_tblProductComment] FOREIGN KEY([ProductID])
    REFERENCES [dbo].[tblProduct] ([ProductID])
);

/****** Object:  Index [UQ__tblAccou__78A989FEC8C38C26]    Script Date: 13/05/2024 10:37:04 CH ******/
ALTER TABLE [dbo].[tblAccount] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC,
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tblAccount]  WITH NOCHECK ADD  CONSTRAINT [FK_AdminID] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblAdmin] ([AdminID])
GO
ALTER TABLE [dbo].[tblAccount] NOCHECK CONSTRAINT [FK_AdminID]
GO
ALTER TABLE [dbo].[tblAccount]  WITH NOCHECK ADD  CONSTRAINT [FK_CTVID] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblCTV] ([CTVID])
GO
ALTER TABLE [dbo].[tblAccount] NOCHECK CONSTRAINT [FK_CTVID]
GO
ALTER TABLE [dbo].[tblAccount]  WITH NOCHECK ADD  CONSTRAINT [FK_CustomerID] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblCustomer] ([CustomerID])
GO
ALTER TABLE [dbo].[tblAccount] NOCHECK CONSTRAINT [FK_CustomerID]
GO
ALTER TABLE [dbo].[tblAccount]  WITH NOCHECK ADD  CONSTRAINT [FK_EmloyeeID] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblEmployee] ([EmployeeID])
GO
ALTER TABLE [dbo].[tblAccount] NOCHECK CONSTRAINT [FK_EmloyeeID]
GO
ALTER TABLE [dbo].[tblBill]  WITH CHECK ADD  CONSTRAINT [FK_CTVID_tblBill] FOREIGN KEY([CTVID])
REFERENCES [dbo].[tblCTV] ([CTVID])
GO
ALTER TABLE [dbo].[tblBill] CHECK CONSTRAINT [FK_CTVID_tblBill]
GO
ALTER TABLE [dbo].[tblBill]  WITH CHECK ADD  CONSTRAINT [FK_CustomerID_tblBill] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[tblCustomer] ([CustomerID])
GO
ALTER TABLE [dbo].[tblBill] CHECK CONSTRAINT [FK_CustomerID_tblBill]
GO
ALTER TABLE [dbo].[tblBill]  WITH CHECK ADD  CONSTRAINT [FK_EmloyeeID_tblBill] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[tblEmployee] ([EmployeeID])
GO
ALTER TABLE [dbo].[tblBill] CHECK CONSTRAINT [FK_EmloyeeID_tblBill]
GO
ALTER TABLE [dbo].[tblBill]  WITH CHECK ADD  CONSTRAINT [FK_Preferential_tblBill] FOREIGN KEY([PreferentialID])
REFERENCES [dbo].[tblPreferential] ([Preferential])
GO
ALTER TABLE [dbo].[tblBill] CHECK CONSTRAINT [FK_Preferential_tblBill]
GO
ALTER TABLE [dbo].[tblCalendar]  WITH CHECK ADD  CONSTRAINT [FK_ServiceID_tblCalendar] FOREIGN KEY([ServiceID])
REFERENCES [dbo].[tblService] ([ServiceID])
GO
ALTER TABLE [dbo].[tblCalendar] CHECK CONSTRAINT [FK_ServiceID_tblCalendar]
GO
ALTER TABLE [dbo].[tblCalendar]  WITH CHECK ADD  CONSTRAINT [FK_ShiftID_tblCalendar] FOREIGN KEY([ShiftID])
REFERENCES [dbo].[tblShift] ([ShiftID])
GO
ALTER TABLE [dbo].[tblCalendar] CHECK CONSTRAINT [FK_ShiftID_tblCalendar]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_BillID_tblOrderDetails] FOREIGN KEY([BillID])
REFERENCES [dbo].[tblBill] ([BillID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_BillID_tblOrderDetails]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH NOCHECK ADD  CONSTRAINT [FK_ProductID_tblOrderDetails] FOREIGN KEY([ProductID])
REFERENCES [dbo].[tblProduct] ([ProductID])
GO
ALTER TABLE [dbo].[tblOrderDetails] NOCHECK CONSTRAINT [FK_ProductID_tblOrderDetails]
GO
ALTER TABLE [dbo].[tblServiceBill]  WITH CHECK ADD  CONSTRAINT [FK_CalendarID_tblServiceBill] FOREIGN KEY([ShiftID], [ServiceID], [SetDay])
REFERENCES [dbo].[tblCalendar] ([ShiftID], [ServiceID], [SetDay])
GO
ALTER TABLE [dbo].[tblServiceBill] CHECK CONSTRAINT [FK_CalendarID_tblServiceBill]
GO
ALTER TABLE [dbo].[tblServiceBill]  WITH CHECK ADD  CONSTRAINT [FK_CustomerID_tblServiceBill] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[tblCustomer] ([CustomerID])
GO
ALTER TABLE [dbo].[tblServiceBill] CHECK CONSTRAINT [FK_CustomerID_tblServiceBill]
GO
ALTER TABLE [dbo].[tblServiceBill]  WITH CHECK ADD  CONSTRAINT [FK_EmloyeeID_tblServiceBill] FOREIGN KEY([EmployeeID])
REFERENCES [dbo].[tblEmployee] ([EmployeeID])
GO
ALTER TABLE [dbo].[tblServiceBill] CHECK CONSTRAINT [FK_EmloyeeID_tblServiceBill]
GO
-- S?a d?i b?ng tblProduct d? liên k?t v?i c?ng tác viên
ALTER TABLE [dbo].[tblProduct]
ADD [CTVID] [varchar](5);

ALTER TABLE [dbo].[tblProduct] WITH CHECK ADD CONSTRAINT [FK_CTVID_tblProduct]
FOREIGN KEY([CTVID])
REFERENCES [dbo].[tblCTV] ([CTVID]);
GO

-- T?o b?ng m?i tblComplaint d? luu thông tin khi?u n?i
CREATE TABLE [dbo].[tblComplaint](
    [ComplaintID] [int] IDENTITY(11,1) NOT NULL,
    [CustomerID] [varchar](5) NOT NULL,
    [ProductID] [varchar](5) NOT NULL,
    [CTVID] [varchar](5) NULL,  -- C?ng tác viên x? lý khi?u n?i
    [EmployeeID] [varchar](5) NULL,  -- Nhân viên x? lý khi?u n?i (n?u du?c chuy?n ti?p)
    [ComplaintText] [nvarchar](500) NOT NULL,
    [ComplaintStatus] [nvarchar](50) NOT NULL DEFAULT 'Pending',  -- Tr?ng thái m?c d?nh là Pending
    [DateFiled] [date] NOT NULL,
    [DateResolved] [date] NULL,
    CONSTRAINT [PK_tblComplaint] PRIMARY KEY CLUSTERED ([ComplaintID]),
    CONSTRAINT [FK_CustomerID_tblComplaint] FOREIGN KEY([CustomerID]) 
        REFERENCES [dbo].[tblCustomer] ([CustomerID]),
    CONSTRAINT [FK_ProductID_tblComplaint] FOREIGN KEY([ProductID]) 
        REFERENCES [dbo].[tblProduct] ([ProductID]),
    CONSTRAINT [FK_CTVID_tblComplaint] FOREIGN KEY([CTVID]) 
        REFERENCES [dbo].[tblCTV] ([CTVID]),
    CONSTRAINT [FK_EmployeeID_tblComplaint] FOREIGN KEY([EmployeeID]) 
        REFERENCES [dbo].[tblEmployee] ([EmployeeID])
);


CREATE TABLE dbo.tblCTVRevenue (
    CTVRevenueID INT IDENTITY(1,1) PRIMARY KEY,
    CTVID VARCHAR(5) NOT NULL,
    TotalRevenue DECIMAL(18,2) DEFAULT 0,
    TotalWithdrawn DECIMAL(18,2) DEFAULT 0,
    CurrentBalance AS (TotalRevenue - TotalWithdrawn),

    CONSTRAINT FK_tblCTVRevenue_tblCTV FOREIGN KEY (CTVID)
        REFERENCES dbo.tblCTV (CTVID)
);