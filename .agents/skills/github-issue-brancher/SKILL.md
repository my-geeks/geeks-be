---
name: github-issue-brancher
description: "Create a local Git branch for a published GitHub issue in this repository. Use when the user asks to create, switch to, prepare, or start a branch for an issue number, using prefixes by issue type: `feature/`, `bugfix/`, or `refactor/`."
---

# GitHub Issue Brancher

## Overview

Create a local branch for a GitHub issue using the repository's branch naming convention. Fetch the issue, infer its type from labels or title, generate a concise branch name, then create and switch to that branch.

## Branch Naming

Use this format:

```text
<type-prefix>/#<issue-number>-<work-summary>
```

Map issue type to prefix:

- Feature issue: `feature/`
- Bug issue: `bugfix/`
- Refactor issue: `refactor/`

Examples:

```text
feature/#3-user-domain-schema-jpa
bugfix/#12-login-error
refactor/#8-user-service-cleanup
```

Always quote branch names in shell commands because `#` can be interpreted as a shell comment:

```bash
git switch -c "feature/#3-user-domain-schema-jpa"
```

## Workflow

1. Confirm the repository and issue:
   - Run `git remote get-url origin` and derive `owner/repo`.
   - Fetch the GitHub issue by number.
   - If the issue is missing or closed, report that before creating a branch.
2. Determine the branch prefix:
   - Prefer labels first: `feature`, `bug`, `refactor`.
   - If labels are missing, use the title prefix: `[Feature]`, `[Bug]`, `[Refactor]`.
   - If still ambiguous, ask one concise question before creating a branch.
3. Generate `<work-summary>`:
   - Summarize the issue title in short English kebab-case.
   - Remove template prefixes like `[Feature]`.
   - Keep meaningful domain terms such as `user`, `university`, `schema`, `jpa`, `login`, `service`.
   - Use 3-6 words when possible.
   - Use lowercase letters, digits, and hyphens only.
4. Check local Git state:
   - Run `git status --short --branch`.
   - If there are uncommitted changes, stop and ask before switching or creating a branch.
   - Use the user-specified base branch when provided.
   - If the user does not specify a base branch, create the issue branch from `develop`.
   - Before creating from `develop`, run `git switch develop` and confirm it succeeds.
5. Create the branch:
   - Check whether the target branch already exists locally.
   - If it exists, ask whether to switch to it.
   - If it does not exist, run `git switch -c "<branch-name>"`.
6. Verify and report:
   - Run `git status --short --branch`.
   - Report the created branch, base branch, issue number, and issue URL.

## Suffix Examples

- `[Feature] 사용자 도메인 DB 스키마 및 JPA 엔티티 설계` -> `user-domain-schema-jpa`
- `[Feature] 대학교 도메인 DB 스키마 및 JPA 엔티티 설계` -> `university-domain-schema-jpa`
- `[Bug] 로그인 실패 시 500 오류 수정` -> `login-failure-500-error`
- `[Refactor] 사용자 서비스 책임 분리` -> `user-service-responsibility`

## Safety Rules

- Do not create or switch branches when the working tree has uncommitted changes unless the user explicitly approves.
- Do not push the branch unless the user explicitly asks.
- Do not create commits as part of this skill.
- Do not silently change the requested branch prefix convention.
- Do not use the global `codex/` prefix for this project's issue branches.
