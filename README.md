
# AI RAG 项目（SpringBoot + DeepSeek）

## 项目简介
基于 SpringBoot 实现的一个简单 RAG（Retrieval-Augmented Generation）系统，
支持通过本地文档增强 AI 问答能力。

## 核心流程
用户提问
→ 向量化（Embedding）
→ 检索数据库相似文本（TopK）
→ 拼接上下文
→ 调用 AI
→ 返回答案

## 技术栈
- SpringBoot
- MyBatis
- MySQL
- DeepSeek API

## 当前进度
- [x] AI接口接入
- [x] 基础项目结构
- [ ] 文档入库
- [ ] 向量化（Embedding）
- [ ] 相似度检索
- [ ] RAG问答

## 启动方式
1. 配置 `application-local.yml`
2. 填写 API Key
3. 启动 SpringBoot怎么创建。md文档