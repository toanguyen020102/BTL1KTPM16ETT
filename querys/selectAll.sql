SELECT * FROM ChiTietHoaDon
SELECT * FROM HoaDon
SELECT * FROM KhachHang
SELECT * FROM ChiTietSanPham
SELECT * FROM SanPham
SELECT * FROM LoaiSanPham
SELECT * FROM TaiKhoan
SELECT * FROM NhanVien

SELECT COUNT(cthd.tenSanPham), SUM(hd.tongPhaiTra) FROM ChiTietHoaDon cthd INNER JOIN HoaDon hd 
ON cthd.maHoaDon = hd.maHoaDon GROUP BY hd.tongPhaiTra, cthd.tenSanPham

SELECT sp.maSanPham, cthd.tenSanPham, COUNT(cthd.tenSanPham) as soLuongBanRa, SUM(cthd.giaBan) as lmao FROM ChiTietHoaDon cthd INNER JOIN HoaDon hd 
ON cthd.maHoaDon = hd.maHoaDon INNER JOIN SanPham sp
ON sp.maSanPham = cthd.maSanPham GROUP BY sp.maSanPham, cthd.tenSanPham, cthd.tenSanPham ORDER BY lmao DESC

SELECT 
  sp.maSanPham, 
  sp.tenSanPham, 
  ctsp.kichCo, 
  ctsp.mauSac, 
  ctsp.giaNhap, 
  ctsp.soLuong 
FROM ChiTietSanPham ctsp JOIN SanPham sp ON ctsp.maSanPham = sp.maSanPham
WHERE mauSac is not null AND kichCo is not null

SELECT DISTINCT
  sp.maSanPham, 
  sp.tenSanPham
FROM ChiTietSanPham ctsp JOIN SanPham sp ON ctsp.maSanPham = sp.maSanPham
WHERE mauSac is not null AND kichCo is not null