---
name: github-issue-publisher
description: Create GitHub issues from a user's work request for this repository. Use when the user asks Codex to issue-ize, split work into GitHub issues, publish/create/register/file issues, or turn feature, bug, refactor, or task descriptions into repository issues using the local `.github/ISSUE_TEMPLATE/*.yml` templates.
---

# GitHub Issue Publisher

## Overview

Turn a user's work request into one or more GitHub issues for the current repository. Always use the repository's local issue templates as the source of truth for issue types, labels, titles, and body sections.

## Workflow

1. Inspect the current repository:
   - Run `git remote get-url origin` and derive `owner/repo` from the GitHub remote.
   - Read `.github/ISSUE_TEMPLATE/*.yml`.
   - If templates are missing, stop and ask whether to create issues without templates.
2. Parse each template:
   - Use `name`, `title`, and `labels` as defaults.
   - For each `body` textarea, use `attributes.label`, `attributes.description`, and `attributes.placeholder` to shape the issue body.
3. Split the user's request:
   - Create one issue per independently deliverable outcome.
   - Do not split by tiny implementation steps such as "write controller", "write service", "write test" unless the user explicitly asks.
   - Prefer fewer, coherent issues over many narrow issues.
   - Classify each issue against the available templates, usually `Feature`, `Bug`, or `Refactor`.
4. Draft issues:
   - Title: keep the template prefix, then write a concise Korean title.
   - Body: use Markdown headings matching template labels.
   - Labels: apply the template labels.
   - Leave unknown facts explicit as `확인 필요` instead of inventing details.
5. Ask for approval before creating anything:
   - Show the repository, issue count, title, labels, and body for each issue.
   - Ask the user to approve, edit, or cancel.
6. After approval, create issues with the GitHub connector:
   - Use the GitHub issue creation tool for each issue.
   - Do not create issues if the connector is unavailable or lacks permission; report the drafted issue content instead.
7. Report results:
   - List each created issue number and URL.
   - Mention any issue that failed to create and include the error.

## Template Body Format

Render each issue body from the selected template's textarea labels:

```md
## <label from template>
<content inferred from user request or `확인 필요`>
```

For task-list fields, use checkbox Markdown:

```md
## 작업 내용
- [ ] 첫 번째 작업
- [ ] 두 번째 작업
```

For bug occurrence steps, keep the project wording `발생 절차`:

```md
## 발생 절차
1. 확인 필요
```

## Classification Heuristics

- Use `Feature` when the request adds user-visible behavior, API capability, or new workflow.
- Use `Bug` when the request describes incorrect behavior, an error, failed expectation, or regression.
- Use `Refactor` when the request improves structure, naming, duplication, dependency direction, or maintainability without intended behavior change.
- If one request mixes unrelated feature, bug, and refactor work, split it into separate issues by outcome and template.
- If classification is ambiguous and creating the wrong issue type would matter, ask one concise question before drafting.

## Safety Rules

- Creating GitHub issues is externally visible. Never create issues without explicit user approval of the draft list.
- Do not push commits, create branches, or open pull requests as part of this skill.
- Do not overwrite or edit local issue template files while publishing issues.
- Do not assume labels exist beyond what templates declare. If GitHub rejects a label, report the failure and offer to retry without that label.
