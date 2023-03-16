USE QuanLyCuaHangBanQuanAo;

INSERT INTO NhanVien values 
('NV02390927102022', N'Nguyễn Đức Long', '0394384569', 1, '2002-12-08','longbao@gmail.com','2021-07-11','231478992136',N'Quan Li',N'Quảng Nam','Tam Ky','Núi Thanh Hội An','45/1 Dinh Bo Linh')
INSERT INTO NhanVien values 
('NV45121027102022', N'Nguyễn Quang Toán', '0452235624', 1, '2002-02-12','tonnguyen@gmail.com','2021-07-11','281476322137',N'Nhan Vien Ban Hang',N'Quảng Nam','Tam Ky','Núi Thanh Hội An','34/5 Truong Cong Dinh')
INSERT INTO NhanVien values 
('NV35321127102022', N'Lê Trọng Nghĩa', '0764670499', 1, '2002-07-07','nghiaabc@gmail.com','2021-07-11','231478992144',N'Nhan Vien Ban Hang',N'Quảng Nam','Tam Ky','Núi Thanh Hội An','903/15 Go Vap')
INSERT INTO NhanVien values 
('NV29521127102022', N'Mai Thị Thu Thúy', '0789112534', 0, '2002-09-28','thybling@gmail.com','2021-07-11','2314666661',N'Nhan Vien Ban Hang',N'Quảng Nam','Tam Ky','Núi Thanh Hội An','354/12 Go Vap')
GO

SELECT * FROM NhanVien

INSERT INTO TaiKhoan VALUES
('admin', 'admin', 'NV02390927102022')
INSERT INTO TaiKhoan VALUES
('letrongnghia', '123456', 'NV35321127102022')
INSERT INTO TaiKhoan VALUES
('toanmicay', '123456', 'NV45121027102022')
INSERT INTO TaiKhoan VALUES
('chothuy', '123456', 'NV29521127102022')

SELECT * FROM TaiKhoan

DELETE FROM NhanVien
DELETE FROM TaiKhoan