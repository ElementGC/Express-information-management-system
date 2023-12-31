USE [master]
GO
/****** Object:  Database [kuaidi]    Script Date: 2023/6/23 9:12:53 ******/
CREATE DATABASE [kuaidi]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'快递', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\快递.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'快递_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\快递_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [kuaidi] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [kuaidi].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [kuaidi] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [kuaidi] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [kuaidi] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [kuaidi] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [kuaidi] SET ARITHABORT OFF 
GO
ALTER DATABASE [kuaidi] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [kuaidi] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [kuaidi] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [kuaidi] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [kuaidi] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [kuaidi] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [kuaidi] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [kuaidi] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [kuaidi] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [kuaidi] SET  DISABLE_BROKER 
GO
ALTER DATABASE [kuaidi] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [kuaidi] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [kuaidi] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [kuaidi] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [kuaidi] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [kuaidi] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [kuaidi] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [kuaidi] SET RECOVERY FULL 
GO
ALTER DATABASE [kuaidi] SET  MULTI_USER 
GO
ALTER DATABASE [kuaidi] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [kuaidi] SET DB_CHAINING OFF 
GO
ALTER DATABASE [kuaidi] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [kuaidi] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'kuaidi', N'ON'
GO
USE [kuaidi]
GO
/****** Object:  Table [dbo].[evaluation]    Script Date: 2023/6/23 9:12:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[evaluation](
	[goodid] [varchar](10) NULL,
	[pid] [varchar](10) NULL,
	[uid] [varchar](10) NULL,
	[evaluation] [varchar](150) NULL,
	[respond] [varchar](150) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[express_info]    Script Date: 2023/6/23 9:12:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[express_info](
	[goodid] [varchar](50) NOT NULL,
	[pid] [varchar](50) NULL,
	[expressposition] [varchar](50) NULL,
	[expressstatus] [varchar](50) NULL,
	[uid] [varchar](50) NULL,
	[receiver] [varchar](50) NULL,
	[receivertel] [varchar](15) NULL,
	[receiverloc] [varchar](50) NULL,
	[sender] [varchar](50) NULL,
	[sendertel] [varchar](15) NULL,
	[senderloc] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[工作人员]    Script Date: 2023/6/23 9:12:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[工作人员](
	[id] [nchar](10) NOT NULL,
	[pw] [int] NOT NULL,
	[staffname] [nchar](10) NULL,
	[staffsex] [nchar](10) NULL,
	[stafftel] [nchar](10) NULL,
	[staffnum] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[快递员]    Script Date: 2023/6/23 9:12:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[快递员](
	[id] [nchar](10) NOT NULL,
	[pw] [int] NOT NULL,
	[postemp] [nchar](10) NULL,
	[postname] [nchar](10) NULL,
	[postsex] [nchar](10) NULL,
	[posttel] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[系统管理员]    Script Date: 2023/6/23 9:12:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[系统管理员](
	[id] [nchar](10) NOT NULL,
	[pw] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[用户]    Script Date: 2023/6/23 9:12:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[用户](
	[id] [varchar](10) NOT NULL,
	[pw] [varchar](15) NOT NULL,
	[username] [varchar](10) NULL,
	[usersex] [varchar](2) NULL,
	[usertel] [varchar](15) NULL,
	[loc] [varchar](50) NULL
) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [kuaidi] SET  READ_WRITE 
GO
